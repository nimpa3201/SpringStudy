package com.example.itemservice.web.basic;


import com.example.itemservice.domain.item.Item;
import com.example.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    // 상품 목록 조회
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items"; // 목록을 보여주는 뷰로 이동
    }

    // 특정 상품 상세 조회
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item"; // 상품 상세 정보를 보여주는 뷰로 이동
    }

    // 상품 등록 폼을 보여줌
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm"; // 상품 등록 폼 뷰로 이동
    }

    // 상품 등록 처리 - V1: @RequestParam 사용
    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model){
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item"; // 상품 등록 후, 해당 상품의 상세 뷰로 이동
    }

    // 상품 등록 처리 - V2: @ModelAttribute 사용 (이름 지정)
    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){
        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute 사용 시 자동으로 추가되므로 생략 가능
        return "basic/item"; // 상품 등록 후, 해당 상품의 상세 뷰로 이동
    }

    // 상품 등록 처리 - V3: @ModelAttribute 사용 (이름 생략)
    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute 사용 시 자동으로 추가되므로 생략 가능
        return "basic/item"; // 상품 등록 후, 해당 상품의 상세 뷰로 이동
    }

    // 상품 등록 처리 - V4: 단순한 객체 전달 방식
    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item"; // 상품 등록 후, 해당 상품의 상세 뷰로 이동
    }

    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }




    //테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,20));
        itemRepository.save(new Item("itemB",20000,30));
    }

}
