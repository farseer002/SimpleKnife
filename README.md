# SimpleKnife
a simple java tool to generator java code with xml as its input:

```xml
<Button
  android:id="@+id/btn_ok"
  android:width="wrap_content"
  android:height="wrap_content"
  />
```

----------------------------------------------------
```java
Button btn_ok;

btn_ok = (Button)findViewById(R.id.btn_ok);

btn_ok.setOnClickListener(this);
```
