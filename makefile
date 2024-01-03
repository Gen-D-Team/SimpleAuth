move:
	cls
	mvn clean -f "d:\Dev4future\MinecraftPluginWorkSpace\SimpleAuth\pom.xml"
	mvn package -f "d:\Dev4future\MinecraftPluginWorkSpace\SimpleAuth\pom.xml"
	del "D:\MinecraftServer\Test\plugins\simpleauthme-0.2-SNAPSHOT.jar"
	move "D:\Dev4future\MinecraftPluginWorkSpace\SimpleAuth\target\simpleauthme-0.2-SNAPSHOT.jar" "D:\MinecraftServer\Test\plugins"
	mvn clean -f "d:\Dev4future\MinecraftPluginWorkSpace\SimpleAuth\pom.xml"
	cd D:\MinecraftServer\Test && start D:\MinecraftServer\Test\start.bat
	cls