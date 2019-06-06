package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.Mapping;

/**
 * Mapping Service
 * 
 * @author YangXinXin
 */
public interface MappingService extends BaseService {
	
	/**
	 * 保存
	 * 
	 * @param mapping
	 * @throws Exception
	 */
	void save(Mapping mapping) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @param mapping
	 * @throws Exception
	 */
	void update(Mapping mapping, String id, String[] properties) throws Exception;
	
	/**
	 * 根据 目标模板ID 获取 所有映射列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<Mapping> findByTargetTemplateId(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据 用户模板ID和目标模板ID 取 映射的列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<Mapping> findMappingListByUserTemplateIdAndTargetTemplateId(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据 目标模板ID 和 目标模板节点ID 查找 映射对象
	 * 
	 * @param targetTemplateId
	 * @param targetTemplateNodeId
	 * @return
	 * @throws Exception
	 */
	Mapping findByTargetTemplateIdAndtargetTemplateNodeId(Map<String, Object> condition) throws Exception;
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	void delete(String id) throws Exception;
	
}
