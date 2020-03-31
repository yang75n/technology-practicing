#编译so
gcc -Wall -fPIC -c Hello.c -I ./ -I $JAVA_HOME/include/linux/ -I $JAVA_HOME/include/
gcc -Wall -rdynamic -shared -o libHello.so Hello.o

#设置动态链接库文件的目录
vi ~/.bash_profile
	#修改添加：
	export LD_LIBRARY_PATH=/home/lib:$LD_LIBRARY_PATH


