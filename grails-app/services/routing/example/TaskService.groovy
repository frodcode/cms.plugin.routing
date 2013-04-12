package routing.example

import routing.example.domain.Task

class TaskService {

    def findAll(LinkedHashMap session) {
        this.initNamespace(session)
        return session.task
    }

    def findBySlug(String slug, LinkedHashMap session)
    {
        this.initNamespace(session)
        return session.task.find{it.slug == slug}
    }

    private def initNamespace(def session)
    {
        if (!session.task) {
            session.task = []
        }
    }

    Task add(String slug, String name, LinkedHashMap session)
    {
        this.initNamespace(session)
        if (findBySlug(slug, session)) {
            throw new IllegalArgumentException(sprintf('Cannot add new task with slug "%s". Slug already exists', slug));
        }
        Task task = new Task(slug: slug, name: name)
        session.task.add(task)
        return task;
    }

    def delete(String slug, LinkedHashMap session)
    {
        this.initNamespace(session)
        Task task = findBySlug(slug, session)
        if (!task) {
            throw new IllegalArgumentException(sprintf('Cannot find task with slug "%s"', slug));
        }
        session.task.remove(session.task.indexOf(task));
    }

    Task edit(String slug, String name, Boolean done, LinkedHashMap session)
    {
        this.initNamespace(session)
        Task task = findBySlug(slug);
        task.name = name
        task.done = done
        return task;
    }

}
