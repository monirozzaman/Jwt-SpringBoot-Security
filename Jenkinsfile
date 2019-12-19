node{
  stage('Clone Project')
  {
  git 'https://github.com/monirozzaman/Jwt-SpringBoot-Security'
  }
  def cloneEmtMaster(slug, repo){

    @NonCPS
    def getSqlInstance() {
        def db   = "jwtUaa"
        def user = "root"
        def pwd  = ""

        def sql = Sql.newInstance(
            "jdbc:mysql://localhost:3306/${db}",
            "${user}",
            "${pwd}",
            "com.mysql.jdbc.Driver"
        )

        return sql
    }

    stage('Build Project')
  {
  sh 'mvn package'
  }
}
