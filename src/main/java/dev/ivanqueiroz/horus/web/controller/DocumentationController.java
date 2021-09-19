package dev.ivanqueiroz.horus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentationController {
  @RequestMapping("/")
  public String index() {
    return "redirect:swagger-ui/index.html";
  }
}
