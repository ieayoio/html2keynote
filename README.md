# html2keynote
This is a conversion project for HTML to keynote

## How to use
```
Html2Key.convert(outPath,htmlFile1,htmlFile12,...);
```
## example
```
String outPath = "~/test"; // out Path
String table1 = "~/table1.html"; // html1 file path
String table2 = "~/table2.html"; // html2 file path

String keynotePath = Html2Key.convert(outPath, table1, table2, table1, table2, table1, table2, table1, table2, table1, table2);
```