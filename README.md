# html2keynote
This is a conversion project for HTML to keynote

## How to use
```
Html2Key.convert(outPath,htmlFile1,htmlFile12,...);
```
## Example
```
String outPath = "~/test"; // out Path
String table1 = "~/table1.html"; // html1 file path
String table2 = "~/table2.html"; // html2 file path

String keynotePath = Html2Key.convert(outPath, table1, table2, table1, table2, table1, table2, table1, table2, table1, table2);
```

## HTML format requirements

1. HTML needs to change the page to horizontal(Otherwise, the display is strange).
2. Chinese fonts only support SimSun.
3. You can set page margins in a @page rule.

## HTML example
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>html to keynote</title>

    <style type="text/css">
        <!--

        @page {
            size: 297mm 160mm;
            margin: 0in;
        }

        body {
            font-family: SimSun;
        }
        -->
    </style>
</head>

<body>
<p>Chinese font testing</p>
<p>中文字体</p>
</body>
</html>

```

## Reference
[Flying Saucer](https://flyingsaucerproject.github.io/flyingsaucer/r8/guide/users-guide-R8.html#xil_36)
