using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class resStore
{
    public long storeId;
    public string userId;
    public long islandId;
    public long storeIslandNum;
    public string storeName;
    public string storeContent;
    public string storeUrl;
    public long storeStatus;

    public bool isOpen;

    public void setStore(long storeId, string userId, long isladId, long storeIslandNum, string storeName, string storeContent, string storeUrl, long storeStatus)
    {
        this.storeId = storeId;
        this.userId = userId;
        this.islandId = isladId;
        this.storeIslandNum = storeIslandNum;
        this.storeName = storeName;
        this.storeContent = storeContent;
        this.storeUrl = storeUrl;
        this.storeStatus = storeStatus;
    }
}
