using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class Store : MonoBehaviour
{

    // 비어있는 상점인지 아닌지
    public bool isOpen;
    // 상점 ID
    public long storeId = -1;
    // 상점 주인 아이디
    public string userId;
    // 섬 번호(카테고리)
    public long islandId;
    // 상점 인덱스
    public long storeIslandNum;
    // 상점 이름
    public string storeName;
    // 상점 간단 설명
    public string storeContent;
    // 상점 URL
    public string storeUrl;
    // 상점 상태 열렸나 닫혔나. 상점주인이 있다 없다 차이.
    public long storeStatus;

    private void Awake()
    {

    }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    public void _newOpenStore()
    {
        transform.GetChild(0).GetComponent<Board>().changeOpen();
    }
    public void _openStore()
    {
        // 상점이 열려있으면 하위 Board를 open으로 바꿔준다.
        // 상점이 열려있고 주인이 있으면
        if(isOpen && storeStatus == 1)
        {
            transform.GetChild(0).GetComponent<Board>().changeOpen();
        }
        // 상점이 열려있고 주인이 없으면
        else if(isOpen && storeStatus == 0)
        {
            transform.GetChild(0).GetComponent<Board>().changeClose();
        }
    }

    //상점 정보 저장하기
    public void setStoreInfo(long storeId, string userId, long isladId, long storeIslandNum, string storeName, string storeContent, string storeUrl, long storeStatus)
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

    public class reqStore
    {
        public long islandId;
        public string storeContent;
        public long storeIslandNum;
        public string storeName;
        public string storeUrl;
        public string userId;
    }
   
}
