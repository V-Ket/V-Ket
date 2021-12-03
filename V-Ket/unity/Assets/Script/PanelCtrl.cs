using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PanelCtrl : MonoBehaviour
{
    public CanvasGroup map1;
    public CanvasGroup map2;
    public CanvasGroup map3;
    public CanvasGroup map4;
    public CanvasGroup square;

    public GameObject sound;

    public void FadeCanvas(int i)
    {
        switch (i)
        {
            case 0:
                Fade(square);
                break;
            case 1:
                Fade(map1);
                break;
            case 2:
                Fade(map2);
                break;
            case 3:
                Fade(map3);
                break;
            case 4:
                Fade(map4);
                break;
        }

    }

    public void Fade(CanvasGroup cg)
    {
        StartCoroutine(FadeTextToFullAlpha(cg));
    }


    IEnumerator FadeTextToFullAlpha(CanvasGroup cg) // 알파값 0에서 1로 전환
    {
        cg.gameObject.SetActive(true);
        cg.alpha = 0f;
        while (cg.alpha < 1.0f)
        {
            cg.alpha += (Time.deltaTime / 1.0f);
            yield return null;
        }
        StartCoroutine(FadeTextToZero(cg));
    }

    IEnumerator FadeTextToZero(CanvasGroup cg)  // 알파값 1에서 0으로 전환
    {
        cg.alpha = 1f;
        while (cg.alpha > 0f)
        {
            cg.alpha -= (Time.deltaTime / 1.0f);
            yield return null;
        }

        cg.gameObject.SetActive(false);
    }


    

}
