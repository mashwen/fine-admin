package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author:aijiaxiang
 * Date:2019/6/26
 * Description:
 */
public class LogModelEnum {

    @AllArgsConstructor
    @Getter
    public enum LogOperationNameEnum{
        CREATE_ROLE("新增角色"),
        UPDATE_ROLE("编辑角色"),
        DELETE_ROLE("删除角色"),
        CREATE_STAFF("新增员工"),
        UPDATE_STAFF("编辑员工"),
        DELETE_STAFF("删除员工"),
        CREATE_RESOURCE("新增资源"),
        UPDATE_RESOURCE("编辑资源"),
        DELETE_RESOURCE("删除资源"),
        CREATE_ORG("新增组织"),
        UPDATE_ORG("编辑组织"),
        DELETE_ORG("删除组织"),
        CREATE_AREA("新增区域"),
        UPDATE_AREA("编辑区域"),
        DELETE_AREA("删除区域");

        private String value;

    }

    @AllArgsConstructor
    @Getter
    public enum LogOperationModuleEnum{
        FINE_ROLE("fine_role","角色管理"),
        FINE_RESOURCE("fine_resource","资源管理"),
        FINE_ORG("fine_org","组织管理"),
        FINE_STAFF("fine_staff","员工管理"),
        FINE_AREA("fine_staff","区域管理");

        private String code;
        private String name;

        public static String getValueByCode(String code){
            for(LogOperationModuleEnum logOperationModuleEnum:LogOperationModuleEnum.values()){
                if(code.equals(logOperationModuleEnum.getCode())){
                    return logOperationModuleEnum.getName();
                }
            }
            return  null;
        }
    }

}