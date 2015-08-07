<h1>SecurePassword</h1>
<h3>你可以在Desktop或Android上运行SecurePassword</h3>

<p>SecurePassword意在打造一个跨平台的，统一管理用户名密码的客户端。<BR>
可以在不同平台上，对用户名密码进行云同步。<BR><BR>

云同步使用第三方平台，比如说Onedrive，百度云等，也可以是本地云，您所有的密码都是安全的<BR>（正在开发中...，暂时只支持像OneDrive那样基于文件系统的同步）<BR><BR>

所有密码均为AES-128加密后在文件存储，存储秘钥是您设置的密码的SHA值<BR>
也就是说即使密码文件被盗走，如果得不到您的密码，您所有的密码都是安全的<BR><BR>


如果所有账户用同一个密码，这样密码安全得不到保障，有很多网站直接对密码**明文**存储在数据库中<BR>
但是如果用不一样的密码，记起来又特别费心，所以我写了这么一个软件<BR>

<BR></p>
<h3>运行环境</h3>
<p>
本软件用java开发<BR><BR>
Desktop版本需要JRE 8以上<BR><BR>
Android版本需要4.0+(SDK15+)(暂时只完成了基本功能)
</p>
<h3>开源协议</h3>
<a target="_blank" href="http://www.gnu.org/licenses/gpl.html"><BR/>
GPL
</a>
<h3>界面预览</h3>
<h4>登录界面</h4>
![](/readme/login.PNG)  ![](/readme/logina.png)
<h4>密码箱</h4>
![](/readme/box.PNG)  ![](/readme/boxa.png)
<h4>创建新的密码</h4>
![](/readme/editor.PNG)  ![](/readme/editora.png)
<h4>菜单</h4>
![](/readme/menu.PNG)  ![](/readme/menua.png)
<h4>导入/导出密码文件</h4>
![](/readme/import.PNG)