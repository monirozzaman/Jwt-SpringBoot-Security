import groovy.sql.Sql
node{
  stage('Clone Project')
  {
  git 'https://github.com/monirozzaman/Jwt-SpringBoot-Security'
  }
    stage('Build Project')
  {
   Class.forName("com.mysql.jdbc.Driver")
   def sql = Sql.newInstance("jdbc:mysql://mysql:3306/test_db", "user","passwd", "com.mysql.jdbc.Driver")
  sh 'mvn package'
  }
}
