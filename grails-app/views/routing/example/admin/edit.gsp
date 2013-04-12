<html>
<head>
    <meta name="layout" content="admin"/>
    <r:require modules="admintypical"/>
    <title>Administrace</title>
</head>
<body>
<div id="header">
    <h1><a href="http://wbpreview.com/previews/WB0F35928/dashboard.html">Unicorn Admin</a></h1>
</div>

<div id="search">
    <input type="text" placeholder="Search here..."/><button type="submit" class="tip-right" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
        <li class="btn btn-inverse" ><a title="" href="index.html#"><i class="icon icon-user"></i> <span class="text">Profile</span></a></li>
        <li class="btn btn-inverse dropdown" id="menu-messages"><a href="index.html#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="index.html#">new message</a></li>
                <li><a class="sInbox" title="" href="index.html#">inbox</a></li>
                <li><a class="sOutbox" title="" href="index.html#">outbox</a></li>
                <li><a class="sTrash" title="" href="index.html#">trash</a></li>
            </ul>
        </li>
        <li class="btn btn-inverse"><a title="" href="index.html#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
        <li class="btn btn-inverse"><r:link title="" singleton="${auth.mc.logoutSlug}"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></r:link></li>
    </ul>
</div>

<div id="sidebar">
    <a href="index.html#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
    <ul>
        <li class="active"><a href="index.html"><i class="icon icon-home"></i> <span>Dashboard</span></a></li>
        <li class="submenu">
            <a href="index.html#"><i class="icon icon-th-list"></i> <span>Form elements</span> <span class="label">3</span></a>
            <ul>
                <li><a href="form-common.html">Common elements</a></li>
                <li><a href="form-validation.html">Validation</a></li>
                <li><a href="form-wizard.html">Wizard</a></li>
            </ul>
        </li>
        <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>Buttons &amp; icons</span></a></li>
        <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Interface elements</span></a></li>
        <li><a href="tables.html"><i class="icon icon-th"></i> <span>Tables</span></a></li>
        <li><a href="grid.html"><i class="icon icon-th-list"></i> <span>Grid Layout</span></a></li>
        <li class="submenu">
            <a href="index.html#"><i class="icon icon-file"></i> <span>Sample pages</span> <span class="label">4</span></a>
            <ul>
                <li><a href="invoice.html">Invoice</a></li>
                <li><a href="chat.html">Support chat</a></li>
                <li><a href="calendar.html">Calendar</a></li>
                <li><a href="gallery.html">Gallery</a></li>
            </ul>
        </li>
        <li>
            <a href="charts.html"><i class="icon icon-signal"></i> <span>Charts &amp; graphs</span></a>
        </li>
        <li>
            <a href="widgets.html"><i class="icon icon-inbox"></i> <span>Widgets</span></a>
        </li>
    </ul>

</div>

<div id="content">
    <div id="content-header">
        <h1>Dashboard</h1>
        <div class="btn-group">
            <a class="btn btn-large tip-bottom" title="Manage Files"><i class="icon-file"></i></a>
            <a class="btn btn-large tip-bottom" title="Manage Users"><i class="icon-user"></i></a>
            <a class="btn btn-large tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a>
            <a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>
        </div>
    </div>
    <div id="breadcrumb">
        <a href="index.html#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="index.html#" class="current">Dashboard</a>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="alert alert-info">
                    Testovací aplikace
                </div>
                <div class="widget-box">
                    <div class="widget-title"><h5>Seznam úkolů</h5><div class="buttons"><a href="index.html#" class="btn btn-mini"><i class="icon-plus"></i> Přidat úkol</a></div></div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>

                                </th>
                                <th>
                                    Název úkolu
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>