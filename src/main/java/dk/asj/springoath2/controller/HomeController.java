package dk.asj.springoath2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/")
  public String index() {
    return "hello from unrestricted endpoint";
  }

  @GetMapping("/secret")
  public String secret() {
    return "hello from secured endpoint";
  }
}
