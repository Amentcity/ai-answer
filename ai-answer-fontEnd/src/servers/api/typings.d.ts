declare namespace API {
  type AiGenerateQuestionRequest = {
    appId?: number
    questionNumber?: number
    optionNumber?: number
  }

  type aiGenerateQuestionSSEParams = {
    aiGenerateQuestionRequest: AiGenerateQuestionRequest
  }

  type aiGenerateQuestionSSETestParams = {
    aiGenerateQuestionRequest: AiGenerateQuestionRequest
    isVip: boolean
  }

  type App = {
    id?: number
    appName?: string
    appDesc?: string
    appIcon?: string
    appType?: number
    scoringStrategy?: number
    reviewStatus?: number
    reviewMessage?: string
    reviewerId?: number
    reviewTime?: string
    userId?: number
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type AppAddRequest = {
    appName?: string
    appDesc?: string
    appIcon?: string
    appType?: number
    scoringStrategy?: number
  }

  type AppAnswerContentDTO = {
    appId?: number
    answerContent?: number
  }

  type AppAnswerResultContentDTO = {
    resultName?: string
    resultContent?: string
  }

  type AppEditRequest = {
    id?: number
    appName?: string
    appDesc?: string
    appIcon?: string
    appType?: number
    scoringStrategy?: number
  }

  type AppQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    appName?: string
    appDesc?: string
    appIcon?: string
    appType?: number
    scoringStrategy?: number
    reviewStatus?: number
    reviewMessage?: string
    reviewerId?: number
    userId?: number
    isDelete?: number
    notId?: number
    searchText?: string
  }

  type AppUpdateRequest = {
    id?: number
    appName?: string
    appDesc?: string
    appIcon?: string
    appType?: number
    scoringStrategy?: number
    reviewStatus?: number
    reviewMessage?: string
    reviewerId?: number
    reviewTime?: string
  }

  type AppVO = {
    id?: number
    appName?: string
    appDesc?: string
    appIcon?: string
    appType?: number
    scoringStrategy?: number
    reviewStatus?: number
    reviewMessage?: string
    reviewerId?: number
    reviewTime?: string
    userId?: number
    createTime?: string
    updateTime?: string
    user?: UserVO
  }

  type BaseResponseAppVO = {
    code?: number
    data?: AppVO
    message?: string
  }

  type BaseResponseBoolean = {
    code?: number
    data?: boolean
    message?: string
  }

  type BaseResponseInteger = {
    code?: number
    data?: number
    message?: string
  }

  type BaseResponseListAppAnswerContentDTO = {
    code?: number
    data?: AppAnswerContentDTO[]
    message?: string
  }

  type BaseResponseListAppAnswerResultContentDTO = {
    code?: number
    data?: AppAnswerResultContentDTO[]
    message?: string
  }

  type BaseResponseListQuestionContentDTO = {
    code?: number
    data?: QuestionContentDTO[]
    message?: string
  }

  type BaseResponseLoginUserVO = {
    code?: number
    data?: LoginUserVO
    message?: string
  }

  type BaseResponseLong = {
    code?: number
    data?: number
    message?: string
  }

  type BaseResponsePageApp = {
    code?: number
    data?: PageApp
    message?: string
  }

  type BaseResponsePageAppVO = {
    code?: number
    data?: PageAppVO
    message?: string
  }

  type BaseResponsePagePost = {
    code?: number
    data?: PagePost
    message?: string
  }

  type BaseResponsePagePostVO = {
    code?: number
    data?: PagePostVO
    message?: string
  }

  type BaseResponsePageQuestion = {
    code?: number
    data?: PageQuestion
    message?: string
  }

  type BaseResponsePageQuestionVO = {
    code?: number
    data?: PageQuestionVO
    message?: string
  }

  type BaseResponsePageScoringResult = {
    code?: number
    data?: PageScoringResult
    message?: string
  }

  type BaseResponsePageScoringResultVO = {
    code?: number
    data?: PageScoringResultVO
    message?: string
  }

  type BaseResponsePageUser = {
    code?: number
    data?: PageUser
    message?: string
  }

  type BaseResponsePageUserAnswer = {
    code?: number
    data?: PageUserAnswer
    message?: string
  }

  type BaseResponsePageUserAnswerVO = {
    code?: number
    data?: PageUserAnswerVO
    message?: string
  }

  type BaseResponsePageUserVO = {
    code?: number
    data?: PageUserVO
    message?: string
  }

  type BaseResponsePostVO = {
    code?: number
    data?: PostVO
    message?: string
  }

  type BaseResponseQuestionVO = {
    code?: number
    data?: QuestionVO
    message?: string
  }

  type BaseResponseScoringResultVO = {
    code?: number
    data?: ScoringResultVO
    message?: string
  }

  type BaseResponseString = {
    code?: number
    data?: string
    message?: string
  }

  type BaseResponseUser = {
    code?: number
    data?: User
    message?: string
  }

  type BaseResponseUserAnswerVO = {
    code?: number
    data?: UserAnswerVO
    message?: string
  }

  type BaseResponseUserVO = {
    code?: number
    data?: UserVO
    message?: string
  }

  type DeleteRequest = {
    id?: number
  }

  type getAppAnswerResultCountParams = {
    appId: number
  }

  type getAppVOByIdParams = {
    id: number
  }

  type getPostVOByIdParams = {
    id: number
  }

  type getQuestionVOByIdParams = {
    id: number
  }

  type getScoringResultVOByIdParams = {
    id: number
  }

  type getUserAnswerVOByIdParams = {
    id: number
  }

  type getUserByIdParams = {
    id: number
  }

  type getUserVOByIdParams = {
    id: number
  }

  type LoginUserVO = {
    id?: number
    userName?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
    createTime?: string
    updateTime?: string
  }

  type Option = {
    result?: string
    score?: number
    value?: string
    key?: string
  }

  type OrderItem = {
    column?: string
    asc?: boolean
  }

  type PageApp = {
    records?: App[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageApp
    searchCount?: PageApp
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageAppVO = {
    records?: AppVO[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageAppVO
    searchCount?: PageAppVO
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PagePost = {
    records?: Post[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PagePost
    searchCount?: PagePost
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PagePostVO = {
    records?: PostVO[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PagePostVO
    searchCount?: PagePostVO
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageQuestion = {
    records?: Question[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageQuestion
    searchCount?: PageQuestion
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageQuestionVO = {
    records?: QuestionVO[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageQuestionVO
    searchCount?: PageQuestionVO
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageScoringResult = {
    records?: ScoringResult[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageScoringResult
    searchCount?: PageScoringResult
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageScoringResultVO = {
    records?: ScoringResultVO[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageScoringResultVO
    searchCount?: PageScoringResultVO
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageUser = {
    records?: User[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageUser
    searchCount?: PageUser
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageUserAnswer = {
    records?: UserAnswer[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageUserAnswer
    searchCount?: PageUserAnswer
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageUserAnswerVO = {
    records?: UserAnswerVO[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageUserAnswerVO
    searchCount?: PageUserAnswerVO
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type PageUserVO = {
    records?: UserVO[]
    total?: number
    size?: number
    current?: number
    orders?: OrderItem[]
    optimizeCountSql?: PageUserVO
    searchCount?: PageUserVO
    optimizeJoinOfCountSql?: boolean
    maxLimit?: number
    countId?: string
    pages?: number
  }

  type Post = {
    id?: number
    title?: string
    content?: string
    tags?: string
    thumbNum?: number
    favourNum?: number
    userId?: number
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type PostAddRequest = {
    title?: string
    content?: string
    tags?: string[]
  }

  type PostEditRequest = {
    id?: number
    title?: string
    content?: string
    tags?: string[]
  }

  type PostFavourAddRequest = {
    postId?: number
  }

  type PostFavourQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    postQueryRequest?: PostQueryRequest
    userId?: number
  }

  type PostQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    notId?: number
    searchText?: string
    title?: string
    content?: string
    tags?: string[]
    orTags?: string[]
    userId?: number
    favourUserId?: number
  }

  type PostThumbAddRequest = {
    postId?: number
  }

  type PostUpdateRequest = {
    id?: number
    title?: string
    content?: string
    tags?: string[]
  }

  type PostVO = {
    id?: number
    title?: string
    content?: string
    thumbNum?: number
    favourNum?: number
    userId?: number
    createTime?: string
    updateTime?: string
    tagList?: string[]
    user?: UserVO
    hasThumb?: boolean
    hasFavour?: boolean
  }

  type Question = {
    id?: number
    questionContent?: string
    appId?: number
    userId?: number
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type QuestionAddRequest = {
    questionContent?: QuestionContentDTO[]
    appId?: number
  }

  type QuestionContentDTO = {
    title?: string
    options?: Option[]
  }

  type QuestionEditRequest = {
    id?: number
    questionContent?: QuestionContentDTO[]
  }

  type QuestionQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    questionContent?: string
    appId?: number
    userId?: number
    notId?: number
  }

  type QuestionUpdateRequest = {
    id?: number
    questionContent?: QuestionContentDTO[]
  }

  type QuestionVO = {
    id?: number
    questionContent?: QuestionContentDTO[]
    appId?: number
    userId?: number
    createTime?: string
    updateTime?: string
    user?: UserVO
  }

  type ReviewRequest = {
    id?: number
    reviewStatus?: number
    reviewMessage?: string
  }

  type ScoringResult = {
    id?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultProp?: string
    resultScoreRange?: number
    appId?: number
    userId?: number
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type ScoringResultAddRequest = {
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultProp?: string[]
    resultScoreRange?: number
    appId?: number
  }

  type ScoringResultEditRequest = {
    id?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultProp?: string[]
    resultScoreRange?: number
  }

  type ScoringResultQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultProp?: string
    resultScoreRange?: number
    appId?: number
    userId?: number
    notId?: number
    searchText?: string
  }

  type ScoringResultUpdateRequest = {
    id?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultProp?: string[]
    resultScoreRange?: number
  }

  type ScoringResultVO = {
    id?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultProp?: string[]
    resultScoreRange?: number
    appId?: number
    userId?: number
    createTime?: string
    updateTime?: string
    user?: UserVO
  }

  type SseEmitter = {
    timeout?: number
  }

  type uploadFileParams = {
    uploadFileRequest: UploadFileRequest
  }

  type UploadFileRequest = {
    biz?: string
  }

  type User = {
    id?: number
    userAccount?: string
    userPassword?: string
    unionId?: string
    mpOpenId?: string
    userName?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type UserAddRequest = {
    userName?: string
    userAccount?: string
    userAvatar?: string
    userRole?: string
  }

  type UserAnswer = {
    id?: number
    appId?: number
    appType?: number
    scoringStrategy?: number
    choices?: string
    resultId?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultScore?: number
    userId?: number
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type UserAnswerAddRequest = {
    appId?: number
    choices?: string[]
    id?: number
  }

  type UserAnswerEditRequest = {
    id?: number
    appId?: number
    choices?: string[]
  }

  type UserAnswerQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    appId?: number
    appType?: number
    scoringStrategy?: number
    choices?: string
    resultId?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultScore?: number
    userId?: number
    createTime?: string
    updateTime?: string
    isDelete?: number
    notId?: number
    searchText?: string
  }

  type UserAnswerUpdateRequest = {
    id?: number
    appId?: number
    choices?: string[]
  }

  type UserAnswerVO = {
    id?: number
    appId?: number
    appType?: number
    scoringStrategy?: number
    choices?: string[]
    resultId?: number
    resultName?: string
    resultDesc?: string
    resultPicture?: string
    resultScore?: number
    userId?: number
    createTime?: string
    updateTime?: string
    user?: UserVO
  }

  type UserLoginRequest = {
    userAccount?: string
    userPassword?: string
  }

  type UserQueryRequest = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    unionId?: string
    mpOpenId?: string
    userName?: string
    userProfile?: string
    userRole?: string
  }

  type UserRegisterRequest = {
    userAccount?: string
    userPassword?: string
    checkPassword?: string
  }

  type UserUpdateMyRequest = {
    userName?: string
    userAvatar?: string
    userProfile?: string
  }

  type UserUpdateRequest = {
    id?: number
    userName?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
  }

  type UserVO = {
    id?: number
    userName?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
    createTime?: string
  }
}
