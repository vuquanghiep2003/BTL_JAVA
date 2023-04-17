
import java.util.ArrayList;
import java.util.Scanner;

// Lớp đại diện cho đối tượng công việc
class Job {
    private String id;
    private String title;
    private String description;

    public Job(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Các phương thức getter và setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Description: " + description;
    }
}

// Lớp quản lý công việc
class JobManager {
    private ArrayList<Job> jobList = new ArrayList<>();

    // Thêm công việc mới
    public void addJob(Job job) {
        jobList.add(job);
    }

    // Sửa thông tin công việc
    public void editJob(String id, String newTitle, String newDescription) {
        for (Job job : jobList) {
            if (job.getId().equals(id)) {
                job.setTitle(newTitle);
                job.setDescription(newDescription);
                break;
            }
        }
    }

    // Xóa công việc
    public void deleteJob(String id) {
        for (Job job : jobList) {
            if (job.getId().equals(id)) {
                jobList.remove(job);
                break;
            }
        }
    }

    // Hiển thị danh sách công việc
    public void displayJobs() {
        System.out.println("Job List:");
        for (Job job : jobList) {
            System.out.println(job.toString());
        }
    }
}

