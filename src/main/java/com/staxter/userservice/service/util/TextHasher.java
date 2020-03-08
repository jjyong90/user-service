package com.staxter.userservice.service.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;
import lombok.SneakyThrows;

/**
 * The type Text hasher.
 */
public class TextHasher {

  /**
   * Hash text string.
   *
   * @param text the text
   * @return the string
   */
  public static String hashText(String text) {
    return Optional.ofNullable(text)
        .map(x -> x.getBytes(StandardCharsets.UTF_8))
        .map(TextHasher::hashBytes)
        .map(x -> new BigInteger(1, x))
        .map(x -> x.toString(16))
        .orElse(null);
  }

  @SneakyThrows
  private static byte[] hashBytes(byte[] bytes) {
    final MessageDigest digest = MessageDigest.getInstance("SHA-512");
    return digest.digest(bytes);
  }

}
