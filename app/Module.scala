import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class Module extends AbstractModule {
  override def configure() = {
    bind(classOf[services.UserService]).to(classOf[services.impl.UserServiceImpl])
    bind(classOf[repositories.UserRepository]).to(classOf[repositories.impl.UserRepositoryImpl])
  }
}