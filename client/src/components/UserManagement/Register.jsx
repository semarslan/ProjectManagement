import React, {Component} from 'react';
import {createUser} from "../../actions/securityActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import classnames from "classnames";

class Register extends Component {

    state = {
        username: "",
        fullName: "",
        password: "",
        confirmPassword: "",
        errors: {}
    }

    componentDidMount() {
        if (this.props.security.validToken) {
            this.props.history.push("/dashboard");
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.errors) {
            this.setState({errors: nextProps.errors})
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const newUser = {
            username: this.state.username,
            fullName: this.state.fullName,
            password: this.state.password,
            confirmPassword: this.state.confirmPassword,
        }

        this.props.createUser(newUser, this.props.history);
    }
    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }

    render() {
        const {errors, username, fullName, password, confirmPassword} = this.state;

        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your Account</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text"
                                           className={classnames("form-control form-control-lg", {
                                               "is-invalid": errors.fullName
                                           })}
                                           placeholder="Full Name"
                                           name="fullName"
                                           required
                                           value={fullName}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.fullName && (
                                            <div className="invalid-feedback">
                                                {errors.fullName}
                                            </div>
                                        )
                                    }
                                </div>
                                <div className="form-group">
                                    <input type="text"
                                           className={classnames("form-control form-control-lg", {
                                               "is-invalid": errors.username
                                           })}
                                           placeholder="Email Address (Username)"
                                           name="username"
                                           value={username}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.username && (
                                            <div className="invalid-feedback">
                                                {errors.username}
                                            </div>
                                        )
                                    }
                                </div>
                                <div className="form-group">
                                    <input type="password"
                                           className={classnames("form-control form-control-lg", {
                                               "is-invalid": errors.password
                                           })}
                                           placeholder="Password"
                                           name="password"
                                           value={password}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.password && (
                                            <div className="invalid-feedback">
                                                {errors.password}
                                            </div>
                                        )
                                    }
                                </div>
                                <div className="form-group">
                                    <input type="password"
                                           className={classnames("form-control form-control-lg", {
                                               "is-invalid": errors.confirmPassword
                                           })}
                                           placeholder="Confirm Password"
                                           name="confirmPassword"
                                           value={confirmPassword}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.confirmPassword && (
                                            <div className="invalid-feedback">
                                                {errors.confirmPassword}
                                            </div>
                                        )
                                    }
                                </div>
                                <input type="submit" className="btn btn-info btn-block mt-4"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Register.propTypes = {
    crateUser: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors,
    security: state.security
})

export default connect(mapStateToProps, {createUser})(Register);