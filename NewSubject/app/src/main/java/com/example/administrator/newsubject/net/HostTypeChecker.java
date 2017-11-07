package com.example.administrator.newsubject.net;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 涂文远
 * @version $Rev$
 * @time 2016/12/24 10:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
//替代枚举的方案，使用IntDef保证类型安全
@IntDef({HostType.JIAO_LIAN, HostType.MOKAO, HostType.SSB,HostType.UGOU})
@Retention(RetentionPolicy.SOURCE)
public @interface HostTypeChecker {

}
