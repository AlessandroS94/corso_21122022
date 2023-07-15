package it.corso.example.thymeleafproject.Controller;

import it.corso.example.thymeleafproject.business.interfaces.AssetBO;
import it.corso.example.thymeleafproject.business.interfaces.CategoryBO;
import it.corso.example.thymeleafproject.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssetController {
    @Autowired
    AssetBO assetBO;

    @Autowired
    CategoryBO categoryBO;

    @GetMapping("/create/asset")
    public String creaAsset(Model model){
        model.addAttribute("asset",new Asset());
        model.addAttribute("categories",categoryBO.getAllCategory());
        return "create";
    }

    @PostMapping("/create/asset")
    public String createAsset(@ModelAttribute Asset asset){
        asset.setName(asset.getName().toUpperCase());
        asset.setCategory(categoryBO.getCategory(asset.getCategory().getId()));
        assetBO.createAsset(asset);
        return "redirect:/index";
    }

    @GetMapping("/delete/asset")
    public String deleteAsset(@RequestParam long id){
        assetBO.deleteAsset(id);
        return "redirect:/index";
    }
}
