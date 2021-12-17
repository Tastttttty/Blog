package com.tasty.controller.admin;

/*
 *@author:zhouxin
 *@date:2021/12/15 20:04
 *@description: 分类Controller层实现
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tasty.model.Type;
import com.tasty.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    //自动装配分类业务层接口实现类
    @Autowired
    TypeService typeService;


//    查看分类的主列表
    @RequestMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "id desc";//降序索引
//        PageHelper.startPage(pageNum, pageSize);这句非常重要，这段代码表示分页的开始，意思是从第pageNum页开始，每页显示pageSize条记录
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Type> types = typeService.getAllType();//取出所有分类
        PageInfo<Type> pageInfo = new PageInfo<Type>(types);
        model.addAttribute("pageInfo", pageInfo);//通过model传递给前端页面
        return "admin/types";
    }

//    跳转至增加页面
    @RequestMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/post_type")
//    @Valid注解：请求数据校验，用来判断前端传来是否有重复的分类
    public String post_type(@Valid Type type, RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        //添加的分类数据库已存在时
        if(type1 != null){
            redirectAttributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        //不存在时,保存到数据库中
        int i = typeService.saveType(type);
        if(i == 0){
            redirectAttributes.addFlashAttribute("message", "添加失败");
        }
        else {
            redirectAttributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";
    }

//    点击单个分类的编辑按钮时，通过获取id值跳转到修改页面,随model将要修改的分类传到下一页面
    @GetMapping("/types/{id}/input")
    public String edit(@PathVariable("id") Long id, Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type", type);
        return "admin/types-edit";
    }

    @PostMapping("/types/update")
    public String editPost( @RequestParam("name") String name,RedirectAttributes attributes,
                           @RequestParam("id" ) String id1){
        //注意这里的id1值是,id 记得切割字符串
        long id = Long.parseLong(id1.substring(1, 3));
        Type type = typeService.getTypeById(id);
        type.setName(name);
        //判断是否重复
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            String url = "redirect:/admin/"+id+"/input";
            return url;
        }
        int i = typeService.updateType(type);
        if(i == 0){
            attributes.addFlashAttribute("message", "添加失败");
        }
        else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";
    }

//    删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
