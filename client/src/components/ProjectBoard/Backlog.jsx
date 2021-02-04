import React, {Component} from 'react';
import ProjectTask from "./ProjectTasks/ProjectTask";

class Backlog extends Component {
    render() {
        const {projectTasksProp} = this.props;

        console.log(this.props)
        const tasks = projectTasksProp.map(projectTask => (
            <ProjectTask key={projectTask.id} projectTask={projectTask}/>
        ))

        let todoItems = [];
        let inProgressItems = [];
        let doneItems = [];

        for (let i=0; i<tasks.length; i++) {
            console.log(tasks[i]);
        }
        return (
            <div>
                {/*/*<!-- Backlog STARTS HERE -->*/}
                <div className="container">
                    <div className="row">
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-secondary text-white">
                                    <h3>TO DO</h3>
                                </div>
                            </div>
                            {tasks}
                        </div>
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-primary text-white">
                                    <h3>In Progress</h3>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-success text-white">
                                    <h3>Done</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Backlog;