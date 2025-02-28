package com.ssg.productmanageapp.controller;

import com.ssg.productmanageapp.dto.*;
import com.ssg.productmanageapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/add")
    public void add() {
        log.info("add get............");
    }

    @PostMapping("/add")
    public String addPost(@Valid ProductAddDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("add post............");
        if (bindingResult.hasErrors()) {
            log.error("error : " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "product/add";
        }
        log.info("productDTO: " + dto);
        productService.addProduct(dto);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("list get............");
        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        PageResponseDTO<ProductDTO> dtoList = productService.getAllProducts(pageRequestDTO);
        log.info("productDTOList: " + dtoList);
        model.addAttribute("responseDTO", dtoList);
    }

    @GetMapping({"/readById" , "/modify"})
    public void read(Long productId, Model model) {
        log.info("readById get........");
        log.info("productId: " + productId);

        ProductDTO productDTO = productService.searchProductById(productId);
        log.info("productDTO: " + productDTO);
        model.addAttribute("dto", productDTO);
    }

    @GetMapping("/readByName")
    public void read(String productName, Model model) {
        log.info("readByName get............");
        log.info("productName: " + productName);

        ProductDTO productDTO = productService.searchProductByName(productName);
        log.info("productDTO: " + productDTO);
        model.addAttribute("productDTO", productDTO);
    }

    @PostMapping("/modify")
    public String modify(@Valid ProductUpdateDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("modify post............");
        log.info("productDTO: " + dto);

        if (bindingResult.hasErrors()) {
            log.error("error : " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("productId", dto.productId());
            return "redirect:/product/readById";
        }

        productService.updateProduct(dto);
        redirectAttributes.addAttribute("productId", dto.productId());
        return "redirect:/product/readById";
    }

    @PostMapping("/remove")
    public String remove(Long productId) {
        log.info("remove post............");
        log.info("productId: " + productId);

        productService.removeProduct(productId);
        return "redirect:/product/list";
    }
}
