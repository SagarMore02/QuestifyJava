package com.questify.questify.controller.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RedirectResponse implements Serializable {

  String token;
  String redirectUrl;
}
