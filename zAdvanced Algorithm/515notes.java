A unique test case id that maps to the ASVS.
Detailed and repeatable (the same steps could be done by anyone who reads the instructions) instructions for how to execute the test case
Expected results when running the test case.  A passing test case would indicate a secure system.
Actual results of running the test case.  
Indicate the CWE for the vulnerability you are testing for






test case#  ASVS#   Unique ID   CWE     N.I.S.T





Repeatable Steps:


Expected Results:












To check for possible XSS vulnerabilities:

Look for input fields whose content (once submitted) is displayed on a page.
Try entering simple HTML tags first to test for input sanitization, such as <h1>
If input sanitization is being performed, check for weaknesses in the sanitization scripts
end







----------------------------------------------------------------------------------------------------




Mitigating 

"injection"
	1. 使用安全的API 避免使用解释器
		prepared statements: pre-compiled parameterized SQL queries
	2. 对输入的特殊字符进行 转译		Sanitize special characters entered
	3. 使用白名单来规范化输入	whitelist  blacklist
	4. duplicate security check on the server side
	5. database framework	use store procedures

		1. PreparedStatement:
			Using a pre-compiled statement set, it has built-in ability to handle SQL injection, just use its setXXX method to pass values.
		2. Filtering incoming parameters using regular expressions:
			list that specifies the format of input that should be rejected
		3. String filtering
		4. Check for illegal characters with specific functions
		5. Unsafe character masking on the client using javascript
		6. Any security checks that are performed on the client side, ensure that these checks are duplicated on the server side.
		7. Database Frameworks
			Use stored procedures, Limit database permissions, Use static analysis tools which can be trained
			to find injection vulnerabilities.

"broken authentication and session management"
	use established login module
	balance remember me and security
	generate random session ID
	logout/expire session at a reasonable amout of time	

"XSS"

	1. input filtering	(blacklist + whitelist)
	2. auto-sanitization libraries (AntisS)
	3. 编码输出 （用来确保输入的字符被视为数据，而不是作为html被浏览器所解析）
		Encoded output (used to ensure that input characters are treated as data and not parsed by the browser as html)


		a) Input filtering (blacklist/whitelist)
		b) Use HTTPOnly cookie flag
		c) Output filtering (blacklist/whitelist)
		d) Available output encoding libraries:
			a. Microsoft’s Anti-XSS library
			b. OWASP ESAPI Encoding module
			c. Encoding rules depend on context (HTML, HTML attribute, Script, URL query string, etc)
		e) HTML Escape Before Inserting Untrusted Data into HTML Element Content
			a. when you want to put untrusted data directly into the HTML body somewhere. This includes
			inside normal tags like div, p, b, td, etc.
		f) Attribute Escape Before Inserting Untrusted Data into HTML Common Attributes
			a. typical attribute values like width, name, value, etc. This should not be used for complex attributes like href, src, style, or any of the event handlers like onmouseover.
		g) URL Escape Before Inserting Untrusted Data into HTML URL Parameter Values



"cross-site request forgery"

	the first step to mitigating cross-site request forgery attack is to ensure no cross-site script vulnerbilities are present.


	1. use anti-CSRF tokens (any operations that changes the state of the web application or user accounts should require a secure random token)
		Nonces(随机数)
			unique per session
			one-time cryptographyically random token that is returned to the client
			the nonce is sent to the client and also saved on the server and compared when the action comes in.
			Can be added through JavaScript, hidden fields, headers, and used with forms and AJAX calls.
		Hash-Based Message Authentication Codes(HMAC)
			Encrypted hash of the page URL combined with the session ID or user ID

	2. check headers
		determine the source the request is coming from
		determine the target the request is going to

	3. require multiple steps for dangerous or critical operations

		Require multiple steps to confirm the operation.
		e.g.:
			Do not allow money to be transferred between accounts after	submitting a single web request.
			Require confirmation steps to validate the request and prevent a cross-site request forgery attack




"sensitive data exposure"


	donot store data unnecessarily
	use right encryption



"security misconfiguration"

	1.自动化安装部署
		
	2.及时了解并部署每个环节的软件更新和补丁信息
		keep up to date with the lastest security patches
	3.实施漏洞扫描和安全审计
		aduit
	4. forced change of default credentials


"Insecure Deserialization"

	The only safe architectural pattern is not to accept serialized objects from untrusted sources
	 or to use serialization mediums that only permit primitive data types.

	Isolating and running code that deserializes in low privilege environments when possible.

"Using Components with Known Vulnerabilities"

	1.识别正在使用的组件和版本，包括所有的依赖
		Identifying components and versions in use, including all dependencies
	2.更新组件或引用的库文件到最新
		Update components or referenced library files to the latest
	3.建立安全策略来管理组件的使用
		Establish a security policy to manage the use of components

"XML external entities"
	防止XXE的最安全方法始终是完全禁用DTD（外部实体）。
	The safest way to prevent XXE is always to disable DTDs (External Entities) completely.
----------------------------------------------------------------------------------------------------

What is an Attack Surface?

	a system‘s attack surface is the subset of the system‘s resources (methods, channels, data)
	potentially used in attacks on the program.

	entry and exit points of a program/system


	how to reduce the attack surface?
		Keep entry and exit points to a minimum and allow users to enable functionality as needed.

	Components change the attack surface:
		• OTS components, platform, applications
		• Third party open source or proprietary libraries
		• Widgets and gadgets loaded at runtime as part of a web project
	what to do?
		• Isolate components as much as possible
		• Configure to only open functionality you will use
		• If the component cannot be configured to comply with your security policy, don’t use it
		• Look at vulnerability history in CVE database
		• Maintain up-to-date components
		• Maintain a healthy distrust
		• Authenticate dataflow
		• Consider data coming in untrusted





direct dependency: functionality exported by a library, API or any software component that is referenced directly by "the program itself".
	在 pom.xml中
transitive dependency: any dependency induced by the components that the program references.
 	传递依赖是程序引用的组件引起的任何依赖。





what makes a vulnerbility expolitable?

	Often, during the course of a "penetration test渗透测试", exploitable vulnerabilities 可利用漏洞 are discovered. 

	a pentration test simulated cyber attack against your computer system to check for exploitable vulnerabilities













----------------------------------------------------------------------------------------------------



Least Privilege







use cryptography 
identify sensitive data and how they should be handled
always consider users
understand how integrating external components change your attack surface
be flexible when considering future changes to objects and actors
























