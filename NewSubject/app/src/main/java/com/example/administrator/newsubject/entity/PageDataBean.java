package com.example.administrator.newsubject.entity;

import java.util.List;

/**
 * Created by twy on 2017/4/5.
 */

public class PageDataBean {
    private String currTime;
    private CustomPageDataBean customPageData;
    private PageBean page;
    private List<PageColumnDatasBean> pageColumnDatas;

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public CustomPageDataBean getCustomPageData() {
        return customPageData;
    }

    public void setCustomPageData(CustomPageDataBean customPageData) {
        this.customPageData = customPageData;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<PageColumnDatasBean> getPageColumnDatas() {
        return pageColumnDatas;
    }

    public void setPageColumnDatas(List<PageColumnDatasBean> pageColumnDatas) {
        this.pageColumnDatas = pageColumnDatas;
    }

    public static class CustomPageDataBean {

        private String createTime;
        private int defaultPcid;
        private String name;
        private int pid;
        private boolean showList;
        private int status;
        private String statusStr;
        private String title;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDefaultPcid() {
            return defaultPcid;
        }

        public void setDefaultPcid(int defaultPcid) {
            this.defaultPcid = defaultPcid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public boolean isShowList() {
            return showList;
        }

        public void setShowList(boolean showList) {
            this.showList = showList;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class PageBean {

        private boolean firstPage;
        private boolean lastPage;
        private int pageNumber;
        private int pageSize;
        private int totalPage;
        private int totalRow;
        private List<ListBean> list;

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(int totalRow) {
            this.totalRow = totalRow;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private boolean dataPriceStatus;
            private boolean dataStockStatus;
            private boolean dataTitleStatus;
            private boolean dataUbeanStatus;
            private int hasChangeShow;
            private LayoutStyleBean layoutStyle;
            private String moduleName;
            private int moduleOrder;
            private int moduleStatus;
            private MoreDataBean moreData;
            private int systemConfigContent;
            private SystemConfigContentDataBean systemConfigContentData;
            private TitleDataBean titleData;
            private int viewType;
            private List<LayoutDatasBean> layoutDatas;

            public boolean isDataPriceStatus() {
                return dataPriceStatus;
            }

            public void setDataPriceStatus(boolean dataPriceStatus) {
                this.dataPriceStatus = dataPriceStatus;
            }

            public boolean isDataStockStatus() {
                return dataStockStatus;
            }

            public void setDataStockStatus(boolean dataStockStatus) {
                this.dataStockStatus = dataStockStatus;
            }

            public boolean isDataTitleStatus() {
                return dataTitleStatus;
            }

            public void setDataTitleStatus(boolean dataTitleStatus) {
                this.dataTitleStatus = dataTitleStatus;
            }

            public boolean isDataUbeanStatus() {
                return dataUbeanStatus;
            }

            public void setDataUbeanStatus(boolean dataUbeanStatus) {
                this.dataUbeanStatus = dataUbeanStatus;
            }

            public int getHasChangeShow() {
                return hasChangeShow;
            }

            public void setHasChangeShow(int hasChangeShow) {
                this.hasChangeShow = hasChangeShow;
            }

            public LayoutStyleBean getLayoutStyle() {
                return layoutStyle;
            }

            public void setLayoutStyle(LayoutStyleBean layoutStyle) {
                this.layoutStyle = layoutStyle;
            }

            public String getModuleName() {
                return moduleName;
            }

            public void setModuleName(String moduleName) {
                this.moduleName = moduleName;
            }

            public int getModuleOrder() {
                return moduleOrder;
            }

            public void setModuleOrder(int moduleOrder) {
                this.moduleOrder = moduleOrder;
            }

            public int getModuleStatus() {
                return moduleStatus;
            }

            public void setModuleStatus(int moduleStatus) {
                this.moduleStatus = moduleStatus;
            }

            public MoreDataBean getMoreData() {
                return moreData;
            }

            public void setMoreData(MoreDataBean moreData) {
                this.moreData = moreData;
            }

            public int getSystemConfigContent() {
                return systemConfigContent;
            }

            public void setSystemConfigContent(int systemConfigContent) {
                this.systemConfigContent = systemConfigContent;
            }

            public SystemConfigContentDataBean getSystemConfigContentData() {
                return systemConfigContentData;
            }

            public void setSystemConfigContentData(SystemConfigContentDataBean systemConfigContentData) {
                this.systemConfigContentData = systemConfigContentData;
            }

            public TitleDataBean getTitleData() {
                return titleData;
            }

            public void setTitleData(TitleDataBean titleData) {
                this.titleData = titleData;
            }

            public int getViewType() {
                return viewType;
            }

            public void setViewType(int viewType) {
                this.viewType = viewType;
            }

            public List<LayoutDatasBean> getLayoutDatas() {
                return layoutDatas;
            }

            public void setLayoutDatas(List<LayoutDatasBean> layoutDatas) {
                this.layoutDatas = layoutDatas;
            }

            public static class LayoutStyleBean {
                /**
                 * backgroundColor : #ffffff
                 * backgroundImage :
                 * heightScale : 0.4
                 * marginBottom : 0
                 * marginTop : 0
                 */

                private String backgroundColor;
                private String backgroundImage;
                private double heightScale;
                private int marginBottom;
                private int marginTop;

                public String getBackgroundColor() {
                    return backgroundColor;
                }

                public void setBackgroundColor(String backgroundColor) {
                    this.backgroundColor = backgroundColor;
                }

                public String getBackgroundImage() {
                    return backgroundImage;
                }

                public void setBackgroundImage(String backgroundImage) {
                    this.backgroundImage = backgroundImage;
                }

                public double getHeightScale() {
                    return heightScale;
                }

                public void setHeightScale(double heightScale) {
                    this.heightScale = heightScale;
                }

                public int getMarginBottom() {
                    return marginBottom;
                }

                public void setMarginBottom(int marginBottom) {
                    this.marginBottom = marginBottom;
                }

                public int getMarginTop() {
                    return marginTop;
                }

                public void setMarginTop(int marginTop) {
                    this.marginTop = marginTop;
                }
            }

            public static class MoreDataBean {
                /**
                 * moreName : 更多
                 * objUrl :
                 * showMore : 2
                 */

                private String moreName;
                private String objUrl;
                private int showMore;

                public String getMoreName() {
                    return moreName;
                }

                public void setMoreName(String moreName) {
                    this.moreName = moreName;
                }

                public String getObjUrl() {
                    return objUrl;
                }

                public void setObjUrl(String objUrl) {
                    this.objUrl = objUrl;
                }

                public int getShowMore() {
                    return showMore;
                }

                public void setShowMore(int showMore) {
                    this.showMore = showMore;
                }
            }

            public static class SystemConfigContentDataBean {
                /**
                 * systemContition :
                 * urlType : 0
                 */

                private String systemContition;
                private int urlType;

                public String getSystemContition() {
                    return systemContition;
                }

                public void setSystemContition(String systemContition) {
                    this.systemContition = systemContition;
                }

                public int getUrlType() {
                    return urlType;
                }

                public void setUrlType(int urlType) {
                    this.urlType = urlType;
                }
            }

            public static class TitleDataBean {
                /**
                 * endTime : 2017-04-13 00:00:00
                 * objUrl :
                 * startTime : 2017-04-05 00:00:00
                 * subTitle : 小的标题
                 * templetTitleStyleCode : TS7
                 * titleStyle : {"charColor":"#1cedbb","subTitleColor":"#1b8f18"}
                 * titleType : 3
                 * value : 大的标题
                 */

                private String endTime;
                private String objUrl;
                private String startTime;
                private String subTitle;
                private String templetTitleStyleCode;
                private TitleStyleBean titleStyle;
                private int titleType;
                private String value;

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getObjUrl() {
                    return objUrl;
                }

                public void setObjUrl(String objUrl) {
                    this.objUrl = objUrl;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getSubTitle() {
                    return subTitle;
                }

                public void setSubTitle(String subTitle) {
                    this.subTitle = subTitle;
                }

                public String getTempletTitleStyleCode() {
                    return templetTitleStyleCode;
                }

                public void setTempletTitleStyleCode(String templetTitleStyleCode) {
                    this.templetTitleStyleCode = templetTitleStyleCode;
                }

                public TitleStyleBean getTitleStyle() {
                    return titleStyle;
                }

                public void setTitleStyle(TitleStyleBean titleStyle) {
                    this.titleStyle = titleStyle;
                }

                public int getTitleType() {
                    return titleType;
                }

                public void setTitleType(int titleType) {
                    this.titleType = titleType;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public static class TitleStyleBean {
                    /**
                     * charColor : #1cedbb
                     * subTitleColor : #1b8f18
                     */

                    private String charColor;
                    private String subTitleColor;

                    public String getCharColor() {
                        return charColor;
                    }

                    public void setCharColor(String charColor) {
                        this.charColor = charColor;
                    }

                    public String getSubTitleColor() {
                        return subTitleColor;
                    }

                    public void setSubTitleColor(String subTitleColor) {
                        this.subTitleColor = subTitleColor;
                    }
                }
            }

            public static class LayoutDatasBean {
                /**
                 * activityEndTime :
                 * activityStartTime :
                 * contentConfig : 1
                 * costPrice : 0
                 * dataOrder : 0
                 * desc :
                 * goodsPrice : 0
                 * imgUrl : https://img2.ugou88.com/71e08316-871d-47af-b377-9f195da7fac6.png
                 * objId : 14
                 * objType : 4
                 * objUrl :
                 * relevanceGoodsStock : true
                 * title :
                 * uBean : 0
                 */

                private String activityEndTime;
                private String activityStartTime;
                private int contentConfig;
                private int costPrice;
                private int dataOrder;
                private String desc;
                private int goodsPrice;
                private String imgUrl;
                private int objId;
                private int objType;
                private String objUrl;
                private boolean relevanceGoodsStock;
                private String title;
                private int uBean;

                public String getActivityEndTime() {
                    return activityEndTime;
                }

                public void setActivityEndTime(String activityEndTime) {
                    this.activityEndTime = activityEndTime;
                }

                public String getActivityStartTime() {
                    return activityStartTime;
                }

                public void setActivityStartTime(String activityStartTime) {
                    this.activityStartTime = activityStartTime;
                }

                public int getContentConfig() {
                    return contentConfig;
                }

                public void setContentConfig(int contentConfig) {
                    this.contentConfig = contentConfig;
                }

                public int getCostPrice() {
                    return costPrice;
                }

                public void setCostPrice(int costPrice) {
                    this.costPrice = costPrice;
                }

                public int getDataOrder() {
                    return dataOrder;
                }

                public void setDataOrder(int dataOrder) {
                    this.dataOrder = dataOrder;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getGoodsPrice() {
                    return goodsPrice;
                }

                public void setGoodsPrice(int goodsPrice) {
                    this.goodsPrice = goodsPrice;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public int getObjId() {
                    return objId;
                }

                public void setObjId(int objId) {
                    this.objId = objId;
                }

                public int getObjType() {
                    return objType;
                }

                public void setObjType(int objType) {
                    this.objType = objType;
                }

                public String getObjUrl() {
                    return objUrl;
                }

                public void setObjUrl(String objUrl) {
                    this.objUrl = objUrl;
                }

                public boolean isRelevanceGoodsStock() {
                    return relevanceGoodsStock;
                }

                public void setRelevanceGoodsStock(boolean relevanceGoodsStock) {
                    this.relevanceGoodsStock = relevanceGoodsStock;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getUBean() {
                    return uBean;
                }

                public void setUBean(int uBean) {
                    this.uBean = uBean;
                }
            }
        }
    }

    public static class PageColumnDatasBean {
        /**
         * enableImmediately : 1
         * name : 目录1
         * order : 0
         * pcid : 31
         * pid : 32
         * status : 1
         * type : 1
         */

        private int enableImmediately;
        private String name;
        private int order;
        private int pcid;
        private int pid;
        private int status;
        private int type;

        public int getEnableImmediately() {
            return enableImmediately;
        }

        public void setEnableImmediately(int enableImmediately) {
            this.enableImmediately = enableImmediately;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getPcid() {
            return pcid;
        }

        public void setPcid(int pcid) {
            this.pcid = pcid;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}
