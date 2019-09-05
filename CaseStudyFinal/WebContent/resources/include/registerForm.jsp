<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <form:input class="au-input au-input--full" type="text" path="username" id="username" placeholder="Enter Desired Username"/>
                                    <br><form:errors path="username"/><br>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <form:input class="au-input au-input--full" type="password" path="password" id="password" placeholder="Enter Desired Password"/>
                                    <br><form:errors path="password"/><br>
                                </div>
                                <div class="form-group">
                                    <label for="password2">Repeat Password</label>
                                    <input class="au-input au-input--full" type="password" name="password2" id="password2" placeholder="Repeat Desired Password"/>
									<br>${messageResult}<br>
                                </div>
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <form:input class="au-input au-input--full" type="text" path="name" id="name" placeholder="Enter Your Name"/>
									<br><form:errors path="name"/><br>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email Address</label>
                                    <form:input class="au-input au-input--full" type="email" path="email" id="email" placeholder="Enter Your Email Address"/>
                                    <br><form:errors path="email"/><br>
                                </div>
                                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">Register</button>
