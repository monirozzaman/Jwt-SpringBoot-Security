node{
  stage('Clone Project')
  {
  git 'https://github.com/monirozzaman/Jwt-SpringBoot-Security'
  }
 

      

  
    stage('Build Project')
  {
      def sql = Sql.newInstance("jdbc:mysql://localhost:3306/jwtUaa","root","","com.mysql.jdbc.Driver")
      sh 'mvn package'
  }
}
