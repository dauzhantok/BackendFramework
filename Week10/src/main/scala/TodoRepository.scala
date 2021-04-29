import scala.concurrent.Future

trait TodoRepository {
  def all(): Future[Seq[Todo]]
  def done(): Future[Seq[Todo]]
  def pending(): Future[Seq[Todo]]
  def create(title: String,createTodo: CreateTodo):Future[Todo]
  def update(id: String, updateTodo: UpdateTodo): Future[Todo]
}

object TodoRepository {
  final case class TodoNotFound(id: String) extends Exception("")
  final case class TodoTitleFound(title: String) extends Exception("")
}