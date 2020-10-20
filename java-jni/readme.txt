#编译so,如果是c++则使用g++，如果引用第三方库，则在第二步连接的时候加上。
gcc -Wall -fPIC -c Hello.c -I ./ -I $JAVA_HOME/include/linux/ -I $JAVA_HOME/include/
gcc -Wall -rdynamic -shared -o libHello.so Hello.o

#设置动态链接库文件的目录
vi ~/.bash_profile
	#修改添加：
	export LD_LIBRARY_PATH=/home/lib:$LD_LIBRARY_PATH


