<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="img/profile_small.jpg" />
                             </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">David Williams</strong>
                             </span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span> </span> </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="profile.html">Profile</a></li>
                        <li><a href="contacts.html">Contacts</a></li>
                        <li><a href="mailbox.html">Mailbox</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout">退出</a></li>
                    </ul>
                </div>
                <div class="logo-element">
                    IN+
                </div>
            </li>
            <@system method="menus_home" userId="${user.id?c}">
                <#if menus?? && menus?size gt 0>
                    <#list menus as item>
                        <li <#if nav_active?contains(item.name)>class="active"</#if> >
                            <a href="<#if item.url?? && item.url!=''>${item.url}<#else>javascript:;</#if>"><i class="fa fa-sitemap"></i> <span class="nav-label">${item.name!}</span>
                            <#if item.children?? && item.children?size gt 0>
                                <span class="fa arrow"></span>
                            </#if>
                            </a>
                            <#if item.children?? && item.children?size gt 0>
                                <ul class="nav nav-second-level collapse">
                                    <#list item.children as child>
                                    <li <#if nav_active?contains(child.name)>class="active"</#if>>
                                        <a href="<#if child.url?? && child.url!=''>${child.url}<#else>javascript:;</#if>">${child.name!}</a>
                                    </li>
                                    </#list>
                                </ul>
                            </#if>
                        </li>
                    </#list>
                </#if>
            </@system>
        </ul>
    </div>
</nav>
