using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Board : MonoBehaviour
{

    private SpriteRenderer spriteRenderer;
    public Sprite Open;
    public Sprite Close;
    public Sprite Empty;
    public bool isOpen;

    public string userId;


    private void Awake()
    {
        spriteRenderer = this.GetComponent<SpriteRenderer>();
        isOpen = false;
    }

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }

    public void changeOpen()
    {
        spriteRenderer.sprite = this.Open;
        this.isOpen = true;
    }

    public void changeClose()
    {
        spriteRenderer.sprite = this.Close;
        this.isOpen = false;
    }

    public void changeBoard()
    {
        if (this.isOpen)
        {
            this.isOpen = false;
            changeClose();
        }
        else
        {
            this.isOpen = true;
            changeOpen();
        }

    }
}
