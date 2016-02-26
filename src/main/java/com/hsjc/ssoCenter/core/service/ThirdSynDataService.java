package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hsjc.ssoCenter.core.annotation.SSOSystemLog;
import com.hsjc.ssoCenter.core.constant.ThirdSynConstant;
import com.hsjc.ssoCenter.core.domain.ThirdClients;
import com.hsjc.ssoCenter.core.mapper.SynMapper;
import com.hsjc.ssoCenter.core.mapper.ThirdClientsMapper;
import com.hsjc.ssoCenter.core.mapper.ThirdFilterMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2016-01-28
 */
@Service
public class ThirdSynDataService extends ApiBaseService{
    private final static Logger logger = Logger.getLogger(ThirdSynDataService.class);

    @Autowired
    ThirdClientsMapper thirdClientsMapper;

    @Autowired
    SynMapper synMapper;

    @Autowired
    ThirdFilterMapper thirdFilterMapper;

    /**
     * @author : zga
     * @date : 2015-12-11
     *
     * 根据clientid查询记录
     * @param thirdClients
     * @return
     */
    public ThirdClients selectByClientId(ThirdClients thirdClients){
        return  thirdClientsMapper.selectByClientId(thirdClients);
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步所有组织机构
     ** @param paramJson
     * @return
     */
    @SSOSystemLog(actionId = 2,description = "同步所有组织机构",module = "接口同步")
    public JSONObject getAllOrganization(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);

        if(!resultJson.getBoolean("flag")) return resultJson;

        Integer currentPage = paramJson.getInteger("currentPage");
        Integer pageSize  =paramJson.getInteger("pageSize");

        if(currentPage == null || currentPage == 0) currentPage = 1;
        if(pageSize == null || pageSize == 0) currentPage = 600;

        try {
            /**
             * 查询第三方过滤表
             */
            HashMap paramMap = new HashMap();
            paramMap.put("trdClientId",paramJson.getString("clientId"));
            PageHelper.startPage(currentPage, pageSize);
            List<HashMap> organizationList = synMapper.selectAllOrganization(paramMap);

            Integer totalNum = synMapper.countAllOrganization(paramMap);
            int totalPage = totalNum / pageSize;
            Integer leftNum = 0;
            if(currentPage <= totalPage){
                leftNum = totalNum - currentPage * pageSize;
                if(organizationList == null || (organizationList!= null && organizationList.size() < 1)){
                    resultJson.put("flag",false);
                    resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                    return resultJson;
                }
                paramJson.put("synCount",pageSize);
            } else {
                paramJson.put("synCount",totalNum - (currentPage - 1) * pageSize);
            }

            /**
             * 记录同步数据总数(tbrestfullog:synCount)
             */
            if((totalNum - (currentPage - 1) * pageSize) <= 0){
                resultJson.put("organization",null);
            } else {
                resultJson.put("organization",(currentPage >= (totalNum + 2) ? null : organizationList));
            }

            resultJson.put("leftNum", leftNum);
        }catch (Exception e){
            logger.debug("getAllOrganization Exception Info:"+e.getMessage());

            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        resultJson.put("requestSynId",paramJson.getString("requestSynId"));
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步增量组织机构
     *
     * @param paramJson
     * @return
     */
    @SSOSystemLog(actionId = 3,description = "同步增量组织机构",module = "接口同步")
    public JSONObject getDifferentOrganization(JSONObject paramJson){
        /**
         * 1、校验请求参数信息
         *
         * 2、查询数据
         *  1)、查询数据会结合tb3rdfilter表来进行数据的过滤
         *
         * 3、LogInterCeptor记录同步数量,并返回requestSynId
         *
         * 4、请求完成的数据全部删除
         */
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);
        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);
        if(!resultJson.getBoolean("flag")) return resultJson;

        try{
            List<HashMap> organizationList = synMapper.selectDifferentOrganization(thirdClients.getBriefName());

            /**
             * 记录同步数据总数(tbrestfullog:synCount)
             */
            paramJson.put("synCount",(organizationList == null ? 0 : organizationList.size()));

            if(organizationList == null || (organizationList!= null && organizationList.size() < 1)){
                resultJson.put("flag",false);
                resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                return resultJson;
            }
            resultJson.put("organization",organizationList);

            /**
             * 删除同步组织机构表中的数据
             */
            for(int i = 0;i < organizationList.size();i ++ ){
                HashMap hashMap = organizationList.get(i);
                String organizationCode = hashMap.get("organizationCode").toString();

                HashMap paramMap = new HashMap();
                paramMap.put("briefName",thirdClients.getBriefName());
                paramMap.put("organizationCode",organizationCode);

                synMapper.deleteFinishSynOrganizationByOrganizationCode(paramMap);
            }
        } catch (Exception e){
            logger.debug("getDifferentOrganization Exception Info:"+e.getMessage());

            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        resultJson.put("requestSynId",paramJson.getString("requestSynId"));
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步所有用户
     *
     * @param paramJson
     * @return
     */
    @SSOSystemLog(actionId = 4,description = "同步所有用户",module = "接口同步")
    public JSONObject getAllUser(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);

        if(!resultJson.getBoolean("flag")) return resultJson;

        Integer currentPage = paramJson.getInteger("currentPage");
        Integer pageSize  =paramJson.getInteger("pageSize");

        if(currentPage == null || currentPage == 0) currentPage = 1;
        if(pageSize == null || pageSize == 0) currentPage = 600;

        try{
            HashMap paramMap = new HashMap();
            paramMap.put("trdClientId",paramJson.getString("clientId"));

            PageHelper.startPage(currentPage,pageSize);
            List<HashMap> userList = synMapper.selectAllUser(paramMap);

            Integer totalNum = synMapper.countAllUser(paramMap);
            int totalPage = totalNum / pageSize;
            Integer leftNum = 0;
            if(currentPage <= totalPage){
                System.out.println("");
                leftNum = totalNum - currentPage * pageSize;
                if(userList == null || (userList!= null && userList.size() < 1)){
                    resultJson.put("flag",false);
                    resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                    return resultJson;
                }
                paramJson.put("synCount",pageSize);
            } else {
                paramJson.put("synCount",totalNum - (currentPage - 1) * pageSize);
            }

            /**
             * 记录同步数据总数(tbrestfullog:synCount)
             */
            if((totalNum - (currentPage - 1) * pageSize) <= 0){
                resultJson.put("user",null);
            } else {
                resultJson.put("user",(currentPage >= (totalNum + 2) ? null : userList));
            }
            resultJson.put("leftNum",leftNum);
        } catch (Exception e){
            logger.debug("getAllUser Exception Info:"+e.getMessage());

            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        resultJson.put("requestSynId",paramJson.getString("requestSynId"));
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步增量用户
     *
     * @param paramJson
     * @return
     */
    @SSOSystemLog(actionId = 5,description = "同步增量用户",module = "接口同步")
    public JSONObject getDifferentUser(JSONObject paramJson){
        /**
         * 1、校验请求参数信息
         *
         * 2、查询数据
         *  1)、查询数据会结合tb3rdfilter表来进行数据的过滤
         *
         * 3、LogInterCeptor记录同步数量,并返回requestSynId
         *
         * 4、请求完成的数据全部删除
         */
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);
        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);
        if(!resultJson.getBoolean("flag")) return resultJson;

        try {
            List<HashMap> userList = synMapper.selectDifferentUser(thirdClients.getBriefName());

            /**
             * 记录同步数据总数(tbrestfullog:synCount)
             */
            paramJson.put("synCount",(userList == null ? 0 : userList.size()));

            if(userList == null || (userList != null && userList.size() < 1)){
                resultJson.put("flag",false);
                resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                return resultJson;
            }

            resultJson.put("user", userList);

            /**
             * 删除同步用户表中的数据;
             */
            for(int i = 0;i < userList.size();i ++ ){
                HashMap hashMap = userList.get(i);
                int userId = Integer.parseInt(hashMap.get("openId").toString());

                HashMap paramMap = new HashMap();
                paramMap.put("briefName",thirdClients.getBriefName());
                paramMap.put("userId",userId);

                synMapper.deleteFinishSynUserByUserId(paramMap);

                /**
                 * 插入同步详情日志
                 */
                insertSynUserDeailLog(paramJson,userId);
            }
        } catch (Exception e) {
            logger.debug("getDifferentUser Exception Info:"+e.getMessage());
            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        resultJson.put("requestSynId",paramJson.getString("requestSynId"));
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-1-26
     *
     * 查询所有的ThirdClietnt记录
     *
     * @return
     */
    public List<ThirdClients> selectAllThirdClients(String description,Integer currentPage,Integer pageSize){
        if(StringUtils.isEmpty(description)){
            PageHelper.startPage(currentPage,pageSize);
            return thirdClientsMapper.selectAllThirdClients();
        } else{
            return thirdClientsMapper.selectThirdClientByName(description);
        }
    }

    public Integer getPageCount(Integer recordCount,Integer pageSize){
        int pageCount = recordCount / pageSize;
        return pageCount;
    }
}
