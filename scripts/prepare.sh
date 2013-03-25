here=`pwd`
demo_home=$here/../classpath-demo

rm -rf modules
mkdir modules
rm -rf lib
mkdir lib

cd $demo_home
mvn clean install dependency:copy-dependencies
cd $here

ln -s $demo_home/classloader-demo/target/*.jar lib/
ln -s $demo_home/classloader-demo/target/dependency/*.jar lib/
ln -s /opt/java/jboss-as-7.1.3.Final/jboss-modules.jar lib/

java -cp lib/cl-demo.jar fr.sewatech.classpath.example.JBossModuleCreator

cp -R modules/fr/sewatech/conference/message-main/1.0-SNAPSHOT modules/fr/sewatech/conference/message-main/main
cp save/module.xml modules/fr/sewatech/conference/message-main/main/
