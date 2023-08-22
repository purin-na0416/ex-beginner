package com.example.exbeginner.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbeginner.domain.Item;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
  @Autowired 
  private HttpSession session;

  @Autowired 
  private ServletContext application;

  @GetMapping("")
  public String index(Model model) {
    //商品一覧に表示する商品の名前と金額をセット
    Item item1 = new Item("手帳ノート", 1000);
    Item item2 = new Item("文房具セット", 1500);
    Item item3 = new Item("ファイル", 2000);

    //商品一覧がなかったら作る(さっき作った商品を一覧リストに入れる)
    if(application.getAttribute("items") == null) {
      LinkedList<Item> itemList = new LinkedList<>();

      itemList.add(0, item1);
      itemList.add(1, item2);
      itemList.add(2, item3);

      application.setAttribute("items", itemList);
    }

    //カートがなかったら作る(空の商品一覧リストを作る)
    if(session.getAttribute("cart") == null) {
      LinkedList<Item> cart = new LinkedList<>();
      session.setAttribute("cart", cart);
    } 

    //さっき作ったカートを取得し、その要素の金額を合計金額に足していく
    //getAttributeメソッドではオブジェクトが返ってくるので、それを使うために変数に入れる
    List<Item> cart = (List<Item>)session.getAttribute("cart");
    int sum = 0;
    for(Item item : cart) {
      sum += item.getPrice();
    }

    //requestスコープに合計金額を入れる(ボタンが押されるごとに金額が変わるのでrequestスコープを使う)
    model.addAttribute("sum", sum);
    return "item-and-cart";
  }

  @PostMapping("/in-cart")
  public String inCart(int num1) {
    //商品一覧のリストを取得し、インデックス番号がnum1(フォームボタンのvalue値)の要素を取得する
    LinkedList<Item> itemList = (LinkedList<Item>) application.getAttribute("items");
    Item item = itemList.get(num1);

    //カートを取得し、上で取得した商品を追加する
    List<Item> cart = (List<Item>)session.getAttribute("cart");
    cart.add(item);
    session.setAttribute("cart", cart);

    return "redirect:/shopping-cart";
  }

  @PostMapping("/delete")
  public String delete(int num2) {
    //カートを取得し、インデックス番号がnum2(フォームボタンのvalue値)の商品を削除する
    List<Item> cart = (List<Item>)session.getAttribute("cart");
    cart.remove(num2);
    return "redirect:/shopping-cart";
  }

}
