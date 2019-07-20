<template>
  <div class="article-page-footer">
    <license-tag></license-tag>
    <social-section :type="'Article'" :postId="postId"></social-section>
  </div>
</template>

<script type="text/ecmascript-6">
import LicenseTag from '@/components/views/LicenseTag'
import SocialSection from '@/components/views/Comment/SocialSection'

export default {
  components: {
    'license-tag': LicenseTag,
    'social-section': SocialSection
  },
  props: {
    postId: Number
  }
}
</script>
