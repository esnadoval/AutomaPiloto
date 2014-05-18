<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets">
    <head>
        <link rel="stylesheet" type="text/css" href="public/view.css" media="all"/>
        <script type="text/javascript" src="public/view.js"></script>
        <title>Aplicacion del Impuesto Base</title>
    </head>

    <body>

        <div id="header" class="headr">
            <ui:insert name="header">
                <table><tr><td><img src="public/money.png" width="72px" height="72px"/></td><td>Impuesto Base</td></tr></table>
                
                
            </ui:insert>
        </div>

        <div id="content">
            <ui:insert name="content">
            </ui:insert>
        </div>

        <div id="footer">
            <ui:insert name="footer">
                Generador de Aplicaciones de Impuesto
            </ui:insert>
        </div>

    </body>
</html>
