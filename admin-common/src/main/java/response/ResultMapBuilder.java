package response;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 生成结果Map<br>
 * 1、…<br>
 * 2、…<br>
 * Implement: <br>
 * 1、…<br>
 * 2、…<br>
 *
 * @author mengxy[孟祥元][mengxiangyuan @cfilmcloud.com]         Created by mac on 16/12/24.
 */
public interface ResultMapBuilder {
    /**
     * 结果生成Map形式.
     *
     * @return map
     */
    static Map<String, Object> bulider() {
        return new HashMap<String, Object>();
    }

    /**
     * Token结果.
     *
     * @param token the token
     * @return map
     */
    static Map<String, Object> tokenBulider(String token) {
        return new HashMap<String, Object>() {
            {
                put("token", token);
            }
        };
    }

    /**
     * 分页返回.
     *
     * @param recordsTotal the records total
     * @param results      the results
     * @return map
     */
    static Map<String, Object> recordsBulider(Integer recordsTotal, List results) {
        if (org.springframework.util.CollectionUtils.isEmpty(results)) {
            return new HashMap<String, Object>() {
                {
                    put("recordsTotal", 0);
                    put("records", new ArrayList<>());
                }
            };
        }
        return new HashMap<String, Object>() {
            {
                put("recordsTotal", recordsTotal);
                put("records", results);
            }
        };
    }

    /**
     * 全量返回.
     *
     * @param results the results
     * @return map
     */
    static Map<String, Object> recordsBulider(List results) {
        if (CollectionUtils.isEmpty(results)) {
            return new HashMap<String, Object>() {
                {
                    put("records", new ArrayList<>());
                }
            };
        }
        return new HashMap<String, Object>() {
            {
                //put("recordsTotal", results != null ? results.size() : 0);
                put("records", results);
            }
        };
    }

    /**
     * 全量返回.
     *
     * @param results      the results
     * @param stopSellTime the stop sell time
     * @param filmNotice   the film notice
     * @param member       the member
     * @return map
     */
    static Map<String, Object> sessionBulider(List results, Integer stopSellTime, String filmNotice, boolean member) {
        return new HashMap<String, Object>() {
            {
                put("stopSellTime", stopSellTime != null ? stopSellTime : 0);
                put("records", results != null ? results : new ArrayList<>());
                put("filmNotice", filmNotice);
                put("member", member);
            }
        };
    }

    /**
     * 返回数量.
     *
     * @param recordsTotal the records total
     * @return map
     */
    static Map<String, Object> recordsTotalBulider(Integer recordsTotal) {
        return new HashMap<String, Object>() {
            {
                put("recordsTotal", recordsTotal != null ? recordsTotal : 0);
            }
        };
    }

    /**
     * 返回对象返回.
     *
     * @param data the data
     * @return map
     */
    static Map<String, Object> dataBulider(Object data) {
        if (data == null) {
            return new HashMap<String, Object>() {
                {
                    put("record", new HashMap<>());
                }
            };
        }
        return new HashMap<String, Object>() {
            {
                put("record", data);
            }
        };
    }

}
