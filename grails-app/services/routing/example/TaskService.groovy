package routing.example

import routing.example.domain.Task

class TaskService {

    static def ns = 'task'

    def findAll(def session) {
        return session.tasks
    }

    def findBySlug(String slug, def session)
    {
        return session.{this.ns}.find{it.slug == slug}
    }

    def addNew(String slug, String name, LinkedHashMap session)
    {
        if (findBySlug(slug, session)) {
            throw new IllegalArgumentException(sprintf('Cannot add new task with slug "%s". Slug already exists', slug));
        }
        session.{this.ns}.addEntry(new Task(slug: slug, name: name))
    }

    def delete(String slug, LinkedHashMap session)
    {
        Task task = findBySlug(slug, session)
        if (!task) {
            throw new IllegalArgumentException(sprintf('Cannot find task with slug "%s"', slug));
        }
        session.{this.ns}.remove(session.{this.ns}.indexOf(task));

    }

}
