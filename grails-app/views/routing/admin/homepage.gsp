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
        <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
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

<div id="style-switcher">
    <i class="icon-arrow-left icon-white"></i>
    <span>Style:</span>
    <a href="index.html#grey" style="background-color: #555555;border-color: #aaaaaa;"></a>
    <a href="index.html#blue" style="background-color: #2D2F57;"></a>
    <a href="index.html#red" style="background-color: #673232;"></a>
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
            <div class="span12 center" style="text-align: center;">
                <ul class="stat-boxes">
                    <li>
                        <div class="left peity_bar_good"><span>2,4,9,7,12,10,12</span>+20%</div>
                        <div class="right">
                            <strong>36094</strong>
                            Visits
                        </div>
                    </li>
                    <li>
                        <div class="left peity_bar_neutral"><span>20,15,18,14,10,9,9,9</span>0%</div>
                        <div class="right">
                            <strong>1433</strong>
                            Users
                        </div>
                    </li>
                    <li>
                        <div class="left peity_bar_bad"><span>3,5,9,7,12,20,10</span>-50%</div>
                        <div class="right">
                            <strong>8650</strong>
                            Orders
                        </div>
                    </li>
                    <li>
                        <div class="left peity_line_good"><span>12,6,9,23,14,10,17</span>+70%</div>
                        <div class="right">
                            <strong>8650</strong>
                            Orders
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="alert alert-info">
                    Welcome in the <strong>Unicorn Admin Theme</strong>. Don't forget to check all the pages!
                    <a href="index.html#" data-dismiss="alert" class="close">×</a>
                </div>
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-signal"></i></span><h5>Site Statistics</h5><div class="buttons"><a href="index.html#" class="btn btn-mini"><i class="icon-refresh"></i> Update stats</a></div></div>
                    <div class="widget-content">
                        <div class="row-fluid">
                            <div class="span4">
                                <ul class="site-stats">
                                    <li><i class="icon-user"></i> <strong>1433</strong> <small>Total Users</small></li>
                                    <li><i class="icon-arrow-right"></i> <strong>16</strong> <small>New Users (last week)</small></li>
                                    <li class="divider"></li>
                                    <li><i class="icon-shopping-cart"></i> <strong>259</strong> <small>Total Shop Items</small></li>
                                    <li><i class="icon-tag"></i> <strong>8650</strong> <small>Total Orders</small></li>
                                    <li><i class="icon-repeat"></i> <strong>29</strong> <small>Pending Orders</small></li>
                                </ul>
                            </div>
                            <div class="span8">
                                <div class="chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-file"></i></span><h5>Recent Posts</h5><span title="54 total posts" class="label label-info tip-left">54</span></div>
                    <div class="widget-content nopadding">
                        <ul class="recent-posts">
                            <li>
                                <div class="user-thumb">
                                    <img width="40" height="40" alt="User" src="img/demo/av2.jpg">
                                </div>
                                <div class="article-post">
                                    <span class="user-info"> By: neytiri on 2 Aug 2012, 09:27 AM, IP: 186.56.45.7 </span>
                                    <p>
                                        <a href="index.html#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                                    </p>
                                    <a href="index.html#" class="btn btn-primary btn-mini">Edit</a> <a href="index.html#" class="btn btn-success btn-mini">Publish</a> <a href="index.html#" class="btn btn-danger btn-mini">Delete</a>
                                </div>
                            </li>
                            <li>
                                <div class="user-thumb">
                                    <img width="40" height="40" alt="User" src="img/demo/av3.jpg">
                                </div>
                                <div class="article-post">
                                    <span class="user-info"> By: john on on 24 Jun 2012, 04:12 PM, IP: 192.168.24.3 </span>
                                    <p>
                                        <a href="index.html#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                                    </p>
                                    <a href="index.html#" class="btn btn-primary btn-mini">Edit</a> <a href="index.html#" class="btn btn-success btn-mini">Publish</a> <a href="index.html#" class="btn btn-danger btn-mini">Delete</a>
                                </div>
                            </li>
                            <li>
                                <div class="user-thumb">
                                    <img width="40" height="40" alt="User" src="img/demo/av1.jpg">
                                </div>
                                <div class="article-post">
                                    <span class="user-info"> By: michelle on 22 Jun 2012, 02:44 PM, IP: 172.10.56.3 </span>
                                    <p>
                                        <a href="index.html#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                                    </p>
                                    <a href="index.html#" class="btn btn-primary btn-mini">Edit</a> <a href="index.html#" class="btn btn-success btn-mini">Publish</a> <a href="index.html#" class="btn btn-danger btn-mini">Delete</a>
                                </div>
                            </li>
                            <li class="viewall">
                                <a title="View all posts" class="tip-top" href="index.html#"> + View all + </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-comment"></i></span><h5>Recent Comments</h5><span title="88 total comments" class="label label-info tip-left">88</span></div>
                    <div class="widget-content nopadding">
                        <ul class="recent-comments">
                            <li>
                                <div class="user-thumb">
                                    <img width="40" height="40" alt="User" src="img/demo/av1.jpg">
                                </div>
                                <div class="comments">
                                    <span class="user-info"> User: michelle on IP: 172.10.56.3 </span>
                                    <p>
                                        <a href="index.html#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                                    </p>
                                    <a href="index.html#" class="btn btn-primary btn-mini">Edit</a> <a href="index.html#" class="btn btn-success btn-mini">Approve</a> <a href="index.html#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="index.html#" class="btn btn-danger btn-mini">Delete</a>
                                </div>
                            </li>
                            <li>
                                <div class="user-thumb">
                                    <img width="40" height="40" alt="User" src="img/demo/av3.jpg">
                                </div>
                                <div class="comments">
                                    <span class="user-info"> User: john on IP: 192.168.24.3 </span>
                                    <p>
                                        <a href="index.html#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                                    </p>
                                    <a href="index.html#" class="btn btn-primary btn-mini">Edit</a> <a href="index.html#" class="btn btn-success btn-mini">Approve</a> <a href="index.html#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="index.html#" class="btn btn-danger btn-mini">Delete</a>
                                </div>
                            </li>
                            <li>
                                <div class="user-thumb">
                                    <img width="40" height="40" alt="User" src="img/demo/av2.jpg">
                                </div>
                                <div class="comments">
                                    <span class="user-info"> User: neytiri on IP: 186.56.45.7 </span>
                                    <p>
                                        <a href="index.html#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                                    </p>
                                    <a href="index.html#" class="btn btn-primary btn-mini">Edit</a> <a href="index.html#" class="btn btn-success btn-mini">Approve</a> <a href="index.html#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="index.html#" class="btn btn-danger btn-mini">Delete</a>
                                </div>
                            </li>
                            <li class="viewall">
                                <a title="View all comments" class="tip-top" href="index.html#"> + View all + </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box widget-calendar">
                    <div class="widget-title"><span class="icon"><i class="icon-calendar"></i></span><h5>Calendar</h5></div>
                    <div class="widget-content nopadding">
                        <div class="calendar"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div id="footer" class="span12">
                2012 &copy; Unicorn Admin. Brought to you by <a href="https://wrapbootstrap.com/user/diablo9983">diablo9983</a>
            </div>
        </div>
    </div>
</div>
</body>