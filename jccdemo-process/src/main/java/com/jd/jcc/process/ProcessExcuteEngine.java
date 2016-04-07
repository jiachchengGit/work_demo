/**   
* @Title: ProcessScheduler.java 
* @Package com.jd.jcc.engine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:37:42 
* @version V1.0   
*/
package com.jd.jcc.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jcc.process.common.ExcutorThreadPool;
import com.jd.jcc.process.model.ProNodeTypeEnum;
import com.jd.jcc.process.model.ProParam;
import com.jd.jcc.process.model.ProResult;
import com.jd.jcc.process.model.ProcessBean;
import com.jd.jcc.process.nodedefine.BaseProNode;
import com.jd.jcc.process.nodedefine.AggregationProNode;
import com.jd.jcc.process.nodedefine.BranchNodeItem;
import com.jd.jcc.process.nodedefine.BranchProNode;
import com.jd.jcc.process.nodedefine.BusinessProNode;
import com.jd.jcc.process.nodedefine.ParallelProNode;
import com.jd.jcc.process.nodedefine.StartProNode;
import com.jd.jcc.process.nodedefine.SubProNode;
import com.jd.jcc.process.service.BrandItemExpressEngine;
import com.jd.jcc.process.service.BusinessNodeService;
import com.jd.jcc.process.service.IProcessNodeService;

/** 
 * @ClassName: ProcessScheduler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午2:37:42 
 *  
 */
public class ProcessExcuteEngine {
	private Logger log  = LoggerFactory.getLogger(ProcessExcuteEngine.class);
	private BrandItemExpressEngine brandItemExpressEngine;
	private BusinessNodeService businessNodeService;
	private IProcessNodeService processNodeService;
	
	public void excuteProcess(String processId,ProParam param){
		log.info("***开始执行流程*** processId="+processId);
		ProcessBean proBean = getProcessBean(processId);
		startExcuteProcess(param, proBean);
	}

	/**
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:53:03
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckSubProcessNode(BaseProNode node, ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		SubProNode sn = (SubProNode)node;
		ProcessBean proBean = getProcessBean(sn.getSubProcessId());
		startExcuteProcess(param, proBean);
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		return sn.getNextNode();
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午5:33:34
	 * @param param
	 * @param proBean
	 * @return        
	 * @throws 
	 */
	private void startExcuteProcess(ProParam param, ProcessBean proBean) {
		BaseProNode startNode = proBean.getStartNode();
		excuteNodes(param, startNode);
	}

	/**
	 * @Description: 根据节点类型执行节点任务
	 * @Author chenjiacheng
	 * @Date 2016年4月5日 下午2:29:21
	 * @param param
	 * @param startNode
	 * @return   返回下一个执行节点
	 * @throws
	 */
	private BaseProNode excuteNodes(ProParam param, BaseProNode startNode) {
		BaseProNode nextNode = startNode;
		while(nextNode != null){
			String nodeType = nextNode.getNodeType();
			if(ProNodeTypeEnum.start.name().equals(nodeType)){
				nextNode = fuckStartNode(nextNode,param);
			}if(ProNodeTypeEnum.end.name().equals(nodeType)){
				nextNode = fuckEndNode(nextNode,param);
			}if(ProNodeTypeEnum.business.name().equals(nodeType)){
				nextNode = fuckBusinessNode(nextNode,param);
			}if(ProNodeTypeEnum.branch.name().equals(nodeType)){
				nextNode = fuckBranchNode(nextNode,param);
			}if(ProNodeTypeEnum.parallel.name().equals(nodeType)){
				nextNode = fuckParallelNode(nextNode,param);
			}if(ProNodeTypeEnum.aggregation.name().equals(nodeType)){
				//当节点为聚合节点时要退出
				nextNode = fuckAggregationNode(nextNode,param);
				break;
			}if(ProNodeTypeEnum.sub_process.name().equals(nodeType)){
				nextNode = fuckSubProcessNode(nextNode,param);
			}
		}
		return nextNode;
	}

	/**
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:52:44
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckAggregationNode(BaseProNode node,ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		AggregationProNode an = (AggregationProNode)node;
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		return an;
	}

	/**
	 * @param nodeResults  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午4:00:40
	 * @param an
	 * @param param        
	 * @throws 
	 */
	private void doAggregationWork(AggregationProNode an, ProParam param, Map<String, ProParam> parallelResult) {
		
		Set<Entry<String, ProParam>> es = parallelResult.entrySet();
		for(Entry<String, ProParam> e: es){
			String key = e.getKey();
			ProParam value = e.getValue();
			param.getProContext().getResults().addAll(value.getProContext().getResults());
		}
	}

	/**
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:52:03
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckParallelNode(BaseProNode node,ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		ParallelProNode ppn = (ParallelProNode)node;
		List<BaseProNode> nextNodes = ppn.getNextNodes();
		List<ParallelTask> tasks = new ArrayList<ParallelTask>();
		Map<String,ProParam> parallelResult= new HashMap<String,ProParam>();
		for(BaseProNode n:nextNodes){
			ProParam newParam = new ProParam();
			newParam.setRequestParam(param.getRequestParam());
			tasks.add(new ParallelTask(n,newParam));
			parallelResult.put(n.getNodeKey(), newParam);
		}
		//处理聚合策略
		List<BaseProNode> taskResults = ExcutorThreadPool.getPool().excuteTask(tasks, "Parallel-node-task-"+node.getNodeName());
		AggregationProNode aggregationNode = (AggregationProNode)taskResults.get(0);
		doAggregationWork(aggregationNode, param,parallelResult);
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		//聚合节点的下个节点是一样的
		return aggregationNode.getNextNode();
	}
	
	class ParallelTask implements Callable<BaseProNode>{
		private BaseProNode node;
		private ProParam param;
		public ParallelTask(BaseProNode node,ProParam param){
			this.node =node;
			this.param=param;
		}
		public BaseProNode call() throws Exception {
			BaseProNode nextNodes = excuteNodes(param,node);
			return nextNodes;
		}
	}
	
	/**
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:51:42
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckBranchNode(BaseProNode node, ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		BranchProNode bn = (BranchProNode)node;
		String nextNodeKey=doBrandExpress(bn,param);
		for(BaseProNode n: bn.getNextNodes()){
			if(nextNodeKey.equals(n.getNodeKey())){
				return n;
			}
		}
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		return null;
	}

	/** 
	 * @Description: 执行分支表达式
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午3:09:15
	 * @param bn
	 * @param param
	 * @return        
	 * @throws 
	 */
	private String doBrandExpress(BranchProNode bn, ProParam param) {
		List<BranchNodeItem> items = bn.getItems();
		for(BranchNodeItem i:items){
			boolean flag = brandItemExpressEngine.excuteExpress(i.getBranchExpression(),param.getRequestParam());
			if(flag){
				return i.getNextNodeKey();
			}
		}
		return null;
	}

	/**
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:51:26
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckBusinessNode(BaseProNode node, ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		//执行业务逻辑
		BusinessProNode bn = (BusinessProNode)node;
		ProResult pr = doBusinessWork(bn,param);
		param.setResult(pr);
		//执行下个节点
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		return  bn.getNextNode();
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午3:41:02
	 * @param node
	 * @param param
	 * @return        
	 * @throws 
	 */
	private ProResult doBusinessWork(BaseProNode node, ProParam param) {
		ProResult pr = new ProResult();
		Object requestParam = param.getRequestParam();
		Object result = businessNodeService.excuteService(node,requestParam);
		pr.setResult(result);
		pr.setNode(node);
		return pr;
	}

	/**
	 * @param node2 
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:51:05
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckEndNode(BaseProNode node,ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		log.info(">>[流程执行结束]<<--processId="+node.getProcessId());
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		return null;
	}

	/**
	 * @return  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:49:49
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private BaseProNode fuckStartNode(BaseProNode node,ProParam param) {
		log.info("***执行节点BEGINE***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		StartProNode sn = (StartProNode)node;
		BaseProNode nextNode = sn.getNextNode();
		log.info("***执行节点END***,nodeType={},nodeName={}",new String[]{node.getNodeType(),node.getNodeName()});
		return nextNode;
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:42:39
	 * @param processId
	 * @return        
	 * @throws 
	 */
	private ProcessBean getProcessBean(String processId) {
		ProcessBean pb = processNodeService.queryProcessBeanById(processId);
		if(pb == null){
			log.error("***获取流程实例为空***，processId"+processId);
		}
		return pb;
	}
}
