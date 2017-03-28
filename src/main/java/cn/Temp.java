package cn;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by yiban on 2017/1/17.
 */
public class Temp {
    
    

    /**
     * 根据服务端图片url获取客户端名称
     *
     * @param serverImgUrl
     * @return
     */
    public static String getClientImgName(String serverImgUrl) {
        String clientImgName = serverImgUrl;
        if (StringUtils.isNotBlank(serverImgUrl)) {
            if (serverImgUrl.startsWith("http")) {
                clientImgName = serverImgUrl.substring(serverImgUrl.indexOf("/group") + 1).replaceAll("/", "_");
            } else {
                clientImgName = serverImgUrl.replaceAll("/", "_");
            }
        }
        return clientImgName;
    }

    public static void main(String[] args) {
//        String sql = "select account.accountPOID as accountId, secondAccountGroup.accountGroupPOID as secondAccountGroupId, firstAccountGroup.accountGroupPOID as firstAccountGroupId " +
//                "from t_account as account " +
//                "inner join t_account_group as secondAccountGroup on account.accountGroupPOID = secondAccountGroup.accountGroupPOID " +
//                "inner join t_account_group as firstAccountGroup on secondAccountGroup.parentAccountGroupPOID = firstAccountGroup.accountGroupPOID " +
//                "order by account.ordered ";
//        System.out.println(sql);
        Integer in = null;
    }

    
    
}
