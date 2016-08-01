package com.google.android.exoplayer.hls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Iso8601Util {
  private static final DateFormat ISO_8601_DATETIME_PARSER_MS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
      Locale.US);
  private static final DateFormat ISO_8601_DATETIME_PARSER_NO_MS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",
      Locale.US);
  private static final DateFormat ISO_8601_DATETIME_PARSER_NO_ZONE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
      Locale.US);

  private static final DateFormat[] ISO_8601_DATETIME_PARSERS = new DateFormat[] {
      ISO_8601_DATETIME_PARSER_MS,
      ISO_8601_DATETIME_PARSER_NO_MS,
      ISO_8601_DATETIME_PARSER_NO_ZONE
  };

  static {
    ISO_8601_DATETIME_PARSER_NO_ZONE.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  public static Date parseDateTime(String datetimeStr) {
    for (DateFormat parser : ISO_8601_DATETIME_PARSERS) {
      try {
        return parser.parse(datetimeStr);
      } catch (ParseException ignored) {
        // Date parsing errors should not be fatal as we can potentially backfill.
      }
    }

    return null;
  }
}
