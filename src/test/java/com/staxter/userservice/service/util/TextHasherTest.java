package com.staxter.userservice.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TextHasherTest {

  @ParameterizedTest
  @ValueSource(strings = {"z", "aa", "1242fdsfe54654tdg46", "[[{{{«««,,,<<....>>:://??¬¬¬»»»××¥ä³²324sfsf"})
  void hashText(String text) {

    assertEquals(Hashing.sha512().hashString(text, StandardCharsets.UTF_8).toString(), TextHasher.hashText(text));
  }

}
