package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;
/*    5.1: Отдать свою еду (для отображения в таблице, формат List<MealTo>), запрос БЕЗ параметров
      5.2: Отдать свою еду, отфильтрованную по startDate, startTime, endDate, endTime
      5.3: Отдать/удалить свою еду по id, параметр запроса - id еды. Если еда с этим id чужая или отсутствует - NotFoundException
      5.4: Сохранить/обновить еду, параметр запроса - Meal. Если обновляемая еда с этим id чужая или отсутствует - NotFoundException
      5.5: Сервлет мы удалим, а контроллер останется, поэтому возвращать List<MealTo> надо из контроллера. И userId принимать в контроллере НЕЛЬЗЯ (иначе - для чего аторизация?).
      5.6: В концепции REST при update дополнительно принято передавать id (см. AdminRestController.update)
      5.7: Для получения всей своей еды сделайте отдельный getAll без применения фильтра*/
//на этом уровне вытягиваем ИД авторизованного пользователя и пихаем в методы

}