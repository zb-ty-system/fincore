package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.ApproveAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetApprovalListRequest;
import com.zb.fincore.ams.facade.model.AssetApprovalModel;

/**
 * 功能: 资产审核服务接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:41
 * 版本: V1.0
 */
public interface AssetApprovalServiceFacade {

    /**
     * 审核资产
     *
     * @param req 审核资产请求对象
     * @return 审核资产响应对象
     */
    BaseResponse approveAsset(ApproveAssetRequest req);

    /**
     * 查询资产审核记录列表
     *
     * @param req 查询资产审核记录列表请求对象
     * @return 查询资产审核记录列表响应对象
     */
    PageQueryResponse<AssetApprovalModel> queryAssetApprovalList(QueryAssetApprovalListRequest req);
}
