package com.vkontactegallery;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKParameters;

/**
 * Created by Ivan Danilov on 17.11.2015.
 */
public class VKUtils {
    //authorization
    public final static String SCOPE = "friends,messages,email,photos,wall,notify,notifications";

    private final static String PARAM_USER_ID = "user_id";
    private final static String PARAM_USER_IDS = "user_ids";
    private final static String PARAM_OWNER_ID = "owner_id";
    private final static String PARAM_ORDER = "order";
    private final static String PARAM_LIST_ID = "list_id";
    private final static String PARAM_FIELDS = "fields";
    private final static String PARAM_ORDER_VALUE = "hints"; //flag to sort like vk server
    private final static String PARAM_LIST_ID_VALUE = "28"; //Category best friends
    private final static String PARAM_FIELDS_FRIENDS_VALUE = "city,photo_200_orig,bdate,is_friend";
    private final static String PARAM_FIELDS_PROFILE_VALUE = "photo_max_orig,city,bdate,is_friend";
    private final static String PARAM_MESSAGE = "message";
    public final static String SEND_MESSAGE_METHOD_NAME = "messages.send";
    public final static String GET_ALL_PHOTOS_METHOD_NAME = "photos.getAll";
    public final static String ADD_TO_FRIENDS_METHOD_NAME = "friends.add";
    public final static String GET_FRIENDS_METHOD_NAME = "friends.get";
    public final static String GET_USERS_METHOD_NAME = "users.get";
    public final static String ACCOUNT_REGISTER_DEVICE_METHOD_NAME = "account.registerDevice";

    //get all photos
    public final static String PARAM_EXTENDED = "extended";
    public final static String PARAM_PHOTO_SIZES = "photo_sizes";
    public final static String PARAM_SKIP_HIDDEN = "skip_hidden";
    public final static String PARAM_COUNT = "count";
    public final static String PARAM_OFFSET = "offset";

    //register params
    public final static String TOKEN = "token";
    public final static String DEVICE_ID = "device_id";


    public static VKParameters vkGetAllPhotosParams(String ownerId) {
        return vkGetAllPhotosParams(ownerId, "");
    }

    public static VKParameters vkGetRegisterDeviceParams(String token, String deviceId) {
        VKParameters parameters = new VKParameters();
        parameters.put(TOKEN, token);
        parameters.put(DEVICE_ID, deviceId);
        String str = "{\"msg\":\"on\", \"chat\":[\"no_sound\",\"no_text\"], \"friend\":\"on\", \"reply\":\"on\", \"mention\":\"fr_of_fr\"}";
        parameters.put("settings", str);
        return parameters;
    }

    public static VKParameters vkGetAllPhotosParams(String ownerId, String offset) {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_OWNER_ID, ownerId);
        parameters.put(PARAM_EXTENDED, "0");
        parameters.put(PARAM_PHOTO_SIZES, "0");
        parameters.put(PARAM_SKIP_HIDDEN, "0");
        parameters.put(PARAM_COUNT, "200");
        parameters.put(PARAM_OFFSET, offset);
        return parameters;
    }

    public static VKParameters vkGetFriendParams() {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_ID, VKAccessToken.currentToken().userId);
        parameters.put(PARAM_ORDER, PARAM_ORDER_VALUE);
        parameters.put(PARAM_LIST_ID, PARAM_LIST_ID_VALUE);
        parameters.put(PARAM_FIELDS, PARAM_FIELDS_FRIENDS_VALUE);
        return parameters;
    }

    public static VKParameters vkAddToFriendsParams(String userId) {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_ID, userId);
        return parameters;
    }

    public static VKParameters vkGetUserProfileParams(String id) {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_IDS, id);
        parameters.put(PARAM_FIELDS, PARAM_FIELDS_PROFILE_VALUE);
        return parameters;
    }

    public static VKParameters vkGetProfileParams() {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_IDS, VKAccessToken.currentToken().userId);
        parameters.put(PARAM_FIELDS, PARAM_FIELDS_PROFILE_VALUE);
        return parameters;
    }

    public static VKParameters vkGetProfilesParams(String[] userIds) {
        String ids = "";
        for (int i = 0; i < userIds.length; i++) {
            boolean coma = i < userIds.length;
            ids += userIds[i] + (coma ? "," : "");
        }
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_IDS, ids);
        parameters.put(PARAM_FIELDS, PARAM_FIELDS_PROFILE_VALUE);
        return parameters;
    }

    public static VKParameters vkGetProfilesParams(String userIds) {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_IDS, userIds);
        parameters.put(PARAM_FIELDS, PARAM_FIELDS_PROFILE_VALUE);
        return parameters;
    }

    public static VKParameters vkSendMessageParams(String id, String message) {
        VKParameters parameters = new VKParameters();
        parameters.put(PARAM_USER_ID, id != null ? id : "");
        parameters.put(PARAM_MESSAGE, message);
        return parameters;
    }
}
