# **__Gugino-Utils XML Syntax__**

### (NOTE: GUGINO-UTILS IS STILL IN DEV SO SYNTAX MAY CHANGE.)

### Layout:

```xml
    <!--Layout tag, required for any XML layout with its corresponing attribute-->
    <Layout></Layout>

    <!--Attributes-->
    title=""
    width=""
    height=""
    closeOp="" <!-- Acceptable Values:(EXIT, DISPOSE, DO_NOTHING, HIDE)-->
    resize="" <!-- Acceptable Values: (TRUE, FALSE)-->
```

### Components:

```xml
    <!--Label tag, will create a JLabel object and add it to the window with the corresponing attributes-->
    <Label>Example Text</Label> <!--Text inbetween the Label tags will be set as the labels text-->

    <!--Attributes-->
    name="" <!--Name that component will be saved in the Windows.windowsComponents HashMap as-->

    <!--TextField tag, will create a JTextField object and add it to the window with the corresponing attributes-->
    <TextField></TextField><!--Text inbetween the TextField tags will be set as the textfields's text-->

    <!--Attributes-->
    name="" <!--Name that component will be saved in the Windows.windowsComponents HashMap as-->
    width=""
    height=""

    <!--Button tag, will create a JButton object and add it to the window with the corresponing attributes-->
    <Button>Example Text</Button> <!--Text inbetween the Button tags will be set as the button's text-->

    <!--Attributes-->
    name="" <!--Name that component will be saved in the Windows.windowsComponents HashMap as-->
    width=""
    height=""

    <!--CheckBox tag, will create a JCheckBox object and add it to the window with the corresponing attributes-->
    <CheckBox>Example Text</CheckBox> <!--Text inbetween the CheckBox tags will be set as the checkbox's text-->

    <!--Attributes-->
    name="" <!--Name that component will be saved in the Windows.windowsComponents HashMap as-->
    selected="" <!-- Acceptable Values: (TRUE, FALSE)-->
```
