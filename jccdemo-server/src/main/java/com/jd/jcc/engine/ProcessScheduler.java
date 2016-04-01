/**   
* @Title: ProcessScheduler.java 
* @Package com.jd.jcc.engine 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 下午2:37:42 
* @version V1.0   
*/
package com.jd.jcc.engine;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jcc.engine.model.ProNodeTypeEnum;
import com.jd.jcc.engine.model.ProParam;
import com.jd.jcc.engine.model.ProResult;
import com.jd.jcc.engine.model.ProcessBean;
import com.jd.jcc.engine.nodedefine.BaseProNode;
import com.jd.jcc.engine.nodedefine.AggregationProNode;
import com.jd.jcc.engine.nodedefine.BranchProNode;
import com.jd.jcc.engine.nodedefine.BusinessProNode;
import com.jd.jcc.engine.nodedefine.ParallelProNode;
import com.jd.jcc.engine.nodedefine.StartProNode;
import com.jd.jcc.engine.nodedefine.SubProNode;

/** 
 * @ClassName: ProcessScheduler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 下午2:37:42 
 *  
 */
public class ProcessScheduler {
	private Logger log  = LoggerFactory.getLogger(ProcessScheduler.class);
	
	public void excuteProcess(String source,ProParam param){
		String processId = getProcessIdBySource(source);
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
	private void excuteSubProcessNode(BaseProNode node, ProParam param) {
		SubProNode sn = (SubProNode)node;
		ProcessBean proBean = getProcessBean(sn.getSubProcessId());
		startExcuteProcess(param, proBean);
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
		Map<String, BaseProNode> nodes = proBean.getNodes();
		excuteNode(null,startNode,nodes,param);
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:47:32
	 * @param startNode
	 * @param nodes
	 * @param param
	 * @return        
	 * @throws 
	 */
	private void excuteNode(BaseProNode parentNode,BaseProNode node,Map<String, BaseProNode> nodes, ProParam param) {
		String nodeType = node.getNodeType();
		if(ProNodeTypeEnum.start.name().equals(nodeType)){
			excuteStartNode(node,nodes,param);
		}if(ProNodeTypeEnum.end.name().equals(nodeType)){
			excuteEndNode(node,nodes,param);
		}if(ProNodeTypeEnum.business.name().equals(nodeType)){
			excuteBusinessNode(node,nodes,param);
		}if(ProNodeTypeEnum.branch.name().equals(nodeType)){
			excuteBranchNode(node,nodes,param);
		}if(ProNodeTypeEnum.parallel.name().equals(nodeType)){
			excuteParallelNode(node,nodes,param);
		}if(ProNodeTypeEnum.aggregation.name().equals(nodeType)){
			excuteAggregationNode(parentNode,node,nodes,param);
		}if(ProNodeTypeEnum.sub_process.name().equals(nodeType)){
			excuteSubProcessNode(node,param);
		}
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
	private void excuteAggregationNode(BaseProNode parentNode,BaseProNode node,Map<String, BaseProNode> nodes, ProParam param) {
		AggregationProNode an = (AggregationProNode)node;
		List<BaseProNode> parentNodes = an.getParentNodes();
		BaseProNode nextNode = an.getNextNode();
		param.getProContext().getParallelResults().put(parentNode.getNodeKey(), param.getResult());
		boolean flag = true;
		while(flag){
			int i=0;
			for(BaseProNode pn:parentNodes){
				String nodeKey = pn.getNodeKey();
				if(param.getProContext().getParallelResults().containsKey(nodeKey)){
					i=i+1;
				}
			}
			if(i == parentNodes.size()){
				flag = false;
			}else{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
		
		//多线程并发时，只能由一个线程继续往下走
		boolean isBack = checkPallelFlag(an.getNodeKey(),param);
		if(isBack){
			return;
		}else{
			doAggregationWork(an,param);
			excuteNode(an,nextNode, nodes, param);
		}
	}

	/**
	 * @param param  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午5:16:32
	 * @param nodeKey
	 * @return        
	 * @throws 
	 */
	private synchronized boolean checkPallelFlag(String nodeKey, ProParam param) {
		boolean flag = param.getProContext().getAggregationKey().contains(nodeKey);
		if(!flag){
			param.getProContext().getAggregationKey().add(nodeKey);
		}
		return flag;
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午4:00:40
	 * @param an
	 * @param param        
	 * @throws 
	 */
	private void doAggregationWork(AggregationProNode an, ProParam param) {
		
		
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
	private void excuteParallelNode(BaseProNode node,Map<String, BaseProNode> nodes, ProParam param) {
		ParallelProNode ppn = (ParallelProNode)node;
		List<BaseProNode> nextNodes = ppn.getNextNodes();
		for(BaseProNode n:nextNodes){
			doParallelThread(ppn,n,nodes,param);
		}
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午3:20:34
	 * @param n
	 * @param nodes
	 * @param param
	 * @return        
	 * @throws 
	 */
	private void doParallelThread(BaseProNode ppn,BaseProNode n,Map<String, BaseProNode> nodes, ProParam param) {
		new Thread(new ParallelThread(ppn,n,nodes,param),"ParallelThread-"+n.getNodeName()).start();
	}
	
	private class ParallelThread implements Runnable{
		private BaseProNode parentNode;
		private BaseProNode n;
		private Map<String, BaseProNode> nodes;
		private ProParam param;
		public ParallelThread(BaseProNode parentNode,BaseProNode n,Map<String, BaseProNode> nodes, ProParam param){
			this.parentNode=parentNode;
			this.n=n;
			this.nodes=nodes;
			this.param=param;
		}
		public void run() {
			excuteNode(parentNode,n, nodes, param);
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
	private void excuteBranchNode(BaseProNode node,Map<String, BaseProNode> nodes, ProParam param) {
		BranchProNode bn = (BranchProNode)node;
		String nextNodeKey=excuteExpress(bn,param);
		BaseProNode nextNode = nodes.get(nextNodeKey);
		excuteNode(bn,nextNode, nodes, param);
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午3:09:15
	 * @param bn
	 * @param param
	 * @return        
	 * @throws 
	 */
	private String excuteExpress(BranchProNode bn, ProParam param) {
		
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
	private void excuteBusinessNode(BaseProNode node,Map<String, BaseProNode> nodes, ProParam param) {
		//执行业务逻辑
		BusinessProNode bn = (BusinessProNode)node;
		ProResult pr = doBusinessWork(node,param);
		
		param.setResult(pr);
		//执行下个节点
		BaseProNode nextNode = bn.getNextNode();
		excuteNode(bn,nextNode, nodes, param);
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
		
		
		return null;
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
	private ProResult excuteEndNode(BaseProNode node, Map<String, BaseProNode> nodes, ProParam param) {
		log.info(">>[流程执行结束]<<--processId="+node.getProcessId());
		return param.getResult();
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:49:49
	 * @param node
	 * @param nodes
	 * @param param        
	 * @throws 
	 */
	private void excuteStartNode(BaseProNode node,Map<String, BaseProNode> nodes, ProParam param) {
		StartProNode sn = (StartProNode)node;
		BaseProNode nextNode = sn.getNextNode();
		excuteNode(sn,nextNode, nodes, param);
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
		
		
		
		return null;
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月31日 下午2:39:55
	 * @param source
	 * @return        
	 * @throws 
	 */
	private String getProcessIdBySource(String source) {
		
		
		return null;
	}
}
