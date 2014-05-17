<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets">
    <head>
    
       
       <link rel="stylesheet" type="text/css" href="public/view.css" media="all"/>
       <script type="text/javascript" src="public/view.js"></script>
    </head>

    <body>

        <div id="header">
            <ui:insert name="header">
                Header
            </ui:insert>
        </div>

        <div id="content">
            <ui:insert name="content">
            </ui:insert>
        </div>

        <div id="footer">
            <ui:insert name="footer">
                Footer
            </ui:insert>
        </div>

    </body>
</html>
