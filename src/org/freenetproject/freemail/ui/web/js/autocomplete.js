  $(function() {
    var availableTags = [
    "A#",
    "A+",
    "A++",
    "ABAP",
    "ABC",
    "ABC ALGOL",
    "ABLE",
    "ABSET",
    "ABSYS",
    "ACC",
    "Accent",
    "Ace DASL",
    "ACL2",
    "ACT-III",
    "Action!",
    "ActionScript",
    "Ada",
    "Adenine",
    "Agda",
    "Agilent VEE",
    "Agora",
    "AIMMS",
    "Alef",
    "ALF",
    "ALGOL 58",
    "ALGOL 60",
    "ALGOL 68",
    "ALGOL W",
    "Alice",
    "Alma-0",
    "AmbientTalk",
    "Amiga E",
    "AMOS",
    "AMPL",
    "Apex",
    "APL"
    "AppleScript"
    "Arc"
    "ARexx"
    "Argus"
    "AspectJ"
    "Assembly language"
    "ATS"
    "Ateji PX"
    "AutoHotkey"
    "Autocoder"
    "AutoIt"
    "AutoLISP / Visual LISP"
    "Averest"
    "AWK"
    "Axum"

    ];
    $( ".input" ).autocomplete({
      source: availableTags
    });
  });